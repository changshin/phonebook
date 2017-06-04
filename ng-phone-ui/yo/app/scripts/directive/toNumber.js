// http://stackoverflow.com/questions/28114970/angularjs-ng-options-using-number-for-model-does-not-select-initial-value
angular.module('ngphoneUIApp')
.directive('toNumber', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModel) {
      		ngModel.$parsers.push(function(val) {
        		return val != null ? parseInt(val, 10) : null;
     		});
     		ngModel.$formatters.push(function(val) {
        		return val != null ? '' + val : null;
      		});
    	}
    };
});