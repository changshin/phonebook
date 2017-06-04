angular.module('ngphoneUIApp').directive('nocachesrc',['$rootScope','$timeout', function($rootScope,$timeout) {
  return {
	  
	  restrict: 'A',

    link: function(scope, element, attrs) {
      attrs.$observe('nocachesrc', function(noCacheSrc) {
    	  
        noCacheSrc = $rootScope.baseURL + noCacheSrc+ ((noCacheSrc.indexOf('?') < 0) ? '?' : '&') + (new Date()).getTime();
        $timeout(function(){
        	attrs.$set('src', noCacheSrc);
        },10);
      });
    }
  }
}]);