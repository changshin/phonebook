'use strict';

angular.module('ngphoneUIApp').controller('groupUsersCtrl',
function($scope, $rootScope, $filter, $modalInstance, $modal, dbFactory, group) {

	var $translate = $filter('translate');
	$scope.orderName = "-updatedDate";	// default sorting by lastLogged, desc
	$scope.header = "Group Users: " + group.name; 
	$scope.users = [];
	
	$scope.selectedIds = false;
	$scope.selectedAll = false;
	$scope.selection = { ids: {} };


	dbFactory.query('/api/user/list/groups/'+group.id).then(function(response) {
		$scope.users = response.data.records;
		angular.forEach($scope.users, function(user, index) {
        	user.genderName =  $translate('gender.' + user.genderId);
        	$scope.selection.ids[ user.id ] = false;	// default checkbox setting
		});
	});
	
	// canceling a modal.
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.$watchCollection('selection.ids', function(newValue, oldValue){
		$scope.selectedIds = false;
		angular.forEach($scope.users, function(user, index) {
   			if  ( $scope.selection.ids[ user.id ] ) {
   				$scope.selectedIds = true;
   				return;
   			}	
   		});
    });
	
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
				groupsIds:	group.id,
				ids : selectedIds
			};
			dbFactory.saveForm('/api/user/delete/', params ).then(function(response) {
				if (response.data.status == "Success") {
					$modalInstance.close({
						"message" : "Users are deleted."
					});
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
});