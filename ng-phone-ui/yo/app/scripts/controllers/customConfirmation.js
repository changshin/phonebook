angular.module('ngphoneUIApp').controller('ConfirmationCtrl', function ($scope, $modalInstance, callbackYes,modalHeader, modalMsg,$rootScope) {
	
	$scope.modalHeader = modalHeader;
	$scope.modalMsg = modalMsg;
		 
	$scope.yes = function() {
		callbackYes();
		$modalInstance.dismiss('cancel');
	}
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
		  
});
