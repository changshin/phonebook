angular.module('ngphoneUIApp').filter('object2Array', function() {
	  return function(input) {
		    return _.toArray(input);
	  }
});

angular.module('ngphoneUIApp').filter('toArray', function () {
    'use strict';

    return function (obj) {
        if (!(obj instanceof Object)) {
            return obj;
        }
        var result = [];
        angular.forEach(obj, function(obj, key) {
            obj.$key = key;
            result.push(obj);
        });
        return result;
    }
});
angular.module('ngphoneUIApp').filter('orderByKey', function () {
	return function(obj, field, reverse) {
	    var arr=[];
	    console.log(obj);
	    arr = Object.keys(obj)
	      .map(function (key) { return obj[key] })
	      .sort(function(a,b) { return a > b; } );
	    if(reverse) arr.reverse();
	    return arr;
	};
});
angular.module('ngphoneUIApp').filter('randomSrc', function () {
	return function (input) {
        if (input)
            return input + '?r=' + Math.round(Math.random() * 999999);
    }
});