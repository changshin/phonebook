'use strict';
// please create ng-confirm-template script in the same html
angular.module('ngphoneUIApp').directive('ngConfirm', function($modal, $parse) {
	  return {
	    // So the link function is run before ngClick's, which has priority 0
	    priority: -1,

	    link: function(scope, element, attrs) {
	      element.on('click', function(e) {
	        // Don't run ngClick's handler
	        e.stopImmediatePropagation();
	  
	        $modal.open({
	          templateUrl: '/views/template_confirm_modal.html',
	          controller: 'ngConfirmController',
	          size: attrs.ngConfirmSize,	// 'sm', 'md', 'lg'
	          resolve: {
	            message: function() {
	            	return attrs.ngConfirm;
	            },
	            messageWarning: function() {
		            return attrs.ngConfirmWarning;
		        },
	        	messageHeader: function() {
	        		return attrs.ngConfirmHeader;
			    }
	          }
	        }).result.then(function() {
	          // Pass original click as '$event', just like ngClick
	          $parse(attrs.ngClick)(scope, {$event: e});
	        });
	      });
	    }
	  };
	});

// parameters are from resolve option, must be matched sequentially
angular.module('ngphoneUIApp').controller('ngConfirmController', function($scope, $modalInstance, message, messageWarning,messageHeader) {
	  $scope.message = message;
	  $scope.messageWarning = messageWarning;
	  $scope.messageHeader = messageHeader;
	  
	  $scope.yes = function() {
	    $modalInstance.close();
	  };
	  
	  $scope.no = function() {
	    $modalInstance.dismiss();
	  };
	});
