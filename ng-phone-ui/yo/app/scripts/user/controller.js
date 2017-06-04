'use strict';

angular.module('ngphoneUIApp').controller('UserCtrl',
function($scope, $rootScope, $http, $filter, $modal, dbFactory, $anchorScroll) {

	$anchorScroll();

	var $translate = $filter('translate');
	$scope.currentView = "list.html";
	$scope.orderName = "-updated_date";	// default sorting by updatedDate, desc
	$scope.genders = [];
	$scope.groups = [];
	$scope.users = [];
	$scope.userDetails = {};
	$scope.selectedAll = false;
	$scope.selection = { ids: {} };
	$scope.selectedGroups = { ids: {} };
	
	$scope.editUser = function(user) {
    	if  ( user != undefined) {
	    	$scope.userDetails = user;
	    	var ids = [];
	    	if  ( $scope.userDetails.groupsIds != undefined ) {
	    		ids = $scope.userDetails.groupsIds.split(",");
	    	}
	    	$scope.selectedGroups.ids = {};
	   		angular.forEach($scope.groups, function(group, index) {
	   			if ( ids.indexOf(group.id ) > -1 ) {
	   				$scope.selectedGroups.ids[ group.id ] = true;
	   			} else {
		   	   		$scope.selectedGroups.ids[ group.id ] = false;
	   			}
	   		});
    	} else {
    		$scope.userDetails = {};
    		$scope.userDetails.id = 0;
    		$scope.userDetails.groupsIds = null;	// initial
    		$scope.userDetails.genderId = '1';	// default Male
	    	$scope.selectedGroups.ids = {};
	   		angular.forEach($scope.groups, function(group, index) {
	   	   		$scope.selectedGroups.ids[ group.id ] = false;
	   		});
    	}
    	//console.log($scope.userDetails);
    	$scope.currentView = "edit.html";
    }	// end of $scope.editUser
	 
	$scope.cancel = function(message) {
		$scope.currentView = "list.html";
	};
	
	$scope.selectAllOrNot = function() {
		$scope.selection.ids = {};
		$scope.selectedAll = !$scope.selectedAll;
   		angular.forEach($scope.users, function(user, index) {
   			if  ( $scope.selectedAll ) {
   	   			$scope.selection.ids[ user.id ] = true;
   			} else {
   				$scope.selection.ids[ user.id ] = false;
   			}	
   		});
	};
	
	$scope.deleteUsers = function() {
		var selectedIds = "";
		angular.forEach($scope.users, function(user, index) {
   			if  ( $scope.selection.ids[ user.id ] ) {
   				selectedIds = selectedIds + "," + user.id  
   			}	
   		});
		if  ( selectedIds == "") {
			return;
		}
		selectedIds = selectedIds.substr(1);

		function deleteConfirm() {
			var params = {
				ids : selectedIds
			};
			dbFactory.saveForm('/api/user/delete/', params ).then(function(response) {
				if (response.data.status == "Success") {
					$scope.loadUsers("User(s) are deleted");
				} 
			});
		}
		
		var modalInstance = $modal.open({
			templateUrl : 'views/custom_confirmation.html',
			controller : 'ConfirmationCtrl',
			resolve : {
				callbackYes : function() {
					return deleteConfirm;
				},
				modalHeader : function() {
					return "Delete Users?";
				},
				modalMsg : function() {
					return "Are you sure to delete users?";
				}
			}
		});
		
	};	// end of $scope.deleteUsers = function() {
 
	// to check whether the email id already exists or not.
	$scope.isEmailExists = function(form) {
		
		if  ( form.email.$error.pattern ) {
			$rootScope.$broadcast('broadcast_error', $translate('user.error_email_invalid'));
			return; 
		}
		$scope.userDetails.okUseExistEmail = false;
		angular.element(document.getElementById('add'))[0].disabled = false;
		if ($scope.userDetails.email) {
			var params = {
				email : $scope.userDetails.email
			}
			dbFactory.saveForm('/api/user/exist/', params ).then(function(response) {
				if (response.data.result != undefined && response.data.result) {
					$rootScope.$broadcast('broadcast_error', $translate('user.error_email_in_use'));
					angular.element(document.getElementById('add'))[0].disabled = true;
					$scope.userDetails.okUseExistEmail = true;
					$scope.userDetails.useExistEmail = false;
				} 
			});
		}
	};
	
	// Delete user and modal
	$scope.deleteUser = function(user) {
		if (user.id != undefined) {
			dbFactory.delete('/api/user/'+ user.id).then(function(response) {
				$scope.loadUsers(user.firstName + " (" + user.email + ") is deleted.");
				$scope.currentView = "list.html";
			});
		}
	}

	
	$scope.loadUsers = function(message) {
		$scope.genders = [];
		$scope.groups = [];
		$scope.users = [];

		dbFactory.query('/api/user/4users').then(function(response) {
    		$scope.users = response.data.result.users;
    		$scope.groups = response.data.result.groups;
    		$scope.genders = response.data.result.genders;
    		$scope.selection.ids = {};
    		angular.forEach($scope.users, function(user, index) {
    			user.genderId = '' + user.genderId;
    			user.groupId = '' + user.groupId;
            	user.genderName =  $translate('gender.' + user.genderId);
            	//user.groupName  =  $translate('group.' + user.groupId);
            	$scope.selection.ids[ user.id ] = false;	// default checkbox setting
            	//console.log(user);
    		});
    		angular.forEach($scope.groups, function(group, index) {
    			group.id = ''+group.id;		// pulldown, to prevent extra blank top line
    			//group.name =  $translate('group.' + group.id);
    		});
    		angular.forEach($scope.genders, function(gender, index) {
    			gender.id = '' + gender.id;	// pulldown, to prevent extra blank top line
    			gender.name =  $translate('gender.' + gender.id);
    		});
    		if(message != undefined){
				$rootScope.$broadcast('broadcast_success', message);
			}
		});
	}
	
	// check invalid names
	var getInvalidFiledNames = function(formRequired) {
		if  ( formRequired == undefined || formRequired == null ) {
			return "";
		}
		var fieldNames = "";
		var name = ""
		angular.forEach(formRequired, function(field, index){
			name = field.$name;
			fieldNames += name + ", ";
		});
		if  ( fieldNames != "" ) {
			fieldNames = fieldNames.substring(0,fieldNames.length-2);
		}
		return fieldNames;
	}	
	
	// user submit
	$scope.submit = function(form) {

		if (form.$invalid) {
			var fieldNames = "";
			if  ( form.$error.required != undefined && form.$error.required.length > 0 ) {
				fieldNames = getInvalidFiledNames(form.$error.required);
				$rootScope.$broadcast('broadcast_error', "Please type these field(s): " + fieldNames);
			} else if  ( form.$error.minlength != undefined  && form.$error.minlength.length > 0 ) {
				fieldNames = getInvalidFiledNames(form.$error.minlength);
				$rootScope.$broadcast('broadcast_error', "Please check minumum size: " + fieldNames);
			} else	if  (form.$error.ngIntlTelInput != undefined && form.$error.ngIntlTelInput.length > 0)  {
				$rootScope.$broadcast('broadcast_error', "Please check format."  );
			}
			return;
		} else {
			form.invalid = false;
		}
		
		var selectedIds = "";
		angular.forEach($scope.groups, function(group, index) {
   			if  ( $scope.selectedGroups.ids[ group.id ] ) {
   				selectedIds = selectedIds + "," + group.id  
   			}	
   		});
		if  ( selectedIds == "") {
			$rootScope.$broadcast('broadcast_error', "Please select groups."  );
			return;
		}
		selectedIds = selectedIds.substr(1);
		$scope.userDetails.groupsIds = selectedIds;
		//console.log($scope.userDetails);

		var message = " is added.";
		$scope.userDetails.type = 'add';
		if  ( $scope.userDetails.id != undefined ) {
			$scope.userDetails.type = 'edit';
			message = "is updated.";
		}
		
		dbFactory.saveForm('/api/user/save/', $scope.userDetails ).then(function(response) {
			$scope.loadUsers(response.data.result.firstName + " (" + response.data.result.email + ") "+ message);
			$scope.currentView = "list.html";
		});
	};	// end of $scope.submit = function(form) {
			
	// initial loading
	$scope.loadUsers();

		
});
