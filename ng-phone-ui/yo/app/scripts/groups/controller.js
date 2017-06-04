'use strict';

angular.module('ngphoneUIApp').controller('groupsCtrl',
function($scope, $rootScope, $http, $filter, $modal, dbFactory, $anchorScroll) {

	$anchorScroll();
	var $translate = $filter('translate');
	$scope.genders = [];
	$scope.groups = [];
	$scope.selectedRecord = [];
	$scope.showMode = "list";
	
	$scope.labels = [];
	$scope.data = [];
	$scope.options = {legend: {display: true}};
	
	$scope.onClick = function (points, evt) {
		var selectedLabel = points[0]._view.label;
		var selectedGroup = null;
		angular.forEach($scope.groups, function(group, index) {
			if ( selectedLabel == group.name ) {
				selectedGroup = group;
			};
		});
		//console.log(selectedGroup);
		$scope.showGroupUsers(selectedGroup);
    };

		
	$scope.getList = function(message) {
		dbFactory.query('/api/reference/all').then(function(response) {
			$scope.groups = response.data.result.groups;
			$scope.genders = response.data.result.genders;
			$scope.labels = [];
			$scope.data = [];
			angular.forEach($scope.groups, function(group, index) {
				if  ( group.userCount > 0 ) {
					$scope.labels.push(group.name);
					$scope.data.push(group.userCount);
				}
			});
			angular.forEach($scope.genders, function(gender, index) {
				gender.name =  $translate('gender.' + gender.id);
			});
			console.log($scope.genders);
			console.log($scope.groups);
			if(message != undefined){
				$rootScope.$broadcast('broadcast_success', message);
			}
		});
	}
	
	$scope.getList();
	
	
	$scope.deleteRecord = function() {
		dbFactory.delete('/api/groups/'+$scope.selectedRecord.id).then(function(response) {
			$scope.getList($scope.selectedRecord.name + " is deleted.");
		});
		$scope.showMode = "list";
	}
	$scope.addRecord = function() {
		if  ( $scope.showMode == "add") {
			$scope.showMode = "edit";

		} else {
			$scope.selectedRecord = {id: 0,name:''};
			$scope.showMode = "add";
		}
	}

	$scope.editRecord = function(record) {
		if  ( $scope.selectedRecord.id != record.id ) {
    		$scope.showMode = 'edit';
			$scope.selectedRecord = record;
    	} else {
			if  ( $scope.showMode == "edit") {
				$scope.showMode = "list";
			} else {
				$scope.showMode = "edit";
				$scope.selectedRecord = record;
			}
    	}
	}
	
	$scope.cancel = function() {
		$scope.showMode = "list";
	}
	
	$scope.saveRecord = function() {
		if  ( $scope.selectedRecord.name == '' ) {
			$rootScope.$broadcast('broadcast_error',"Please type name.");
			return;
		}
		console.log($scope.selectedRecord);
		var params = {
				id : $scope.selectedRecord.id,
				name:$scope.selectedRecord.name
			};
		//return;
		console.log(params);
		
		dbFactory.saveForm('/api/groups/save',$scope.selectedRecord).then(function(response) {
			console.log(response);
			if  ( $scope.selectedRecord.id == undefined ) {
				$scope.getList($scope.selectedRecord.name + " is added.");
			} else {
				$scope.getList($scope.selectedRecord.name + " is updated.");
			}
			$scope.showMode = "list";
		});
	};
	
	$scope.showGroupUsers = function (group) {
		if  ( group.userCount < 1 ) {
			return;
		}
   	 	var modalInstance = $modal.open({
       		animation: true,
            templateUrl: 'views/group_users.html',
            controller: 'groupUsersCtrl',
            size: 'lg',
            resolve: {
            	group: function() {
                    return group;
                }
            }
        });
   	 	modalInstance.result.then(function(result) {
   	 		if (result != undefined && result.message) {
   	 			$scope.getList(result.message);
     		}
   	 	}, function() {});
    }
	
});
