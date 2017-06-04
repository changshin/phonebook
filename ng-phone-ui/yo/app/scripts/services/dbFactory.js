'use strict';

angular.module('ngphoneUIApp').factory('dbFactory',function($http,$q) {

    var factory = {};
    factory.query = function (uri) {
		return  $http.get(uri)
	};
	factory.save = function (url,object) {
		return $http.post(url, object);
	};
	// save params with only onefile
	factory.saveForm = function (url,params) {
		//console.log("saveWithFile="+url)
		var formData = new FormData();
        $.each(params, function(k, v) {
        	console.log("key="+k + ":" + v);
        	formData.append(k, v);
        });

        return $http.post(url, formData, {
        	transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
	};
	
	// save formdata with multiple files, check controllerUploadFiles.js
	factory.saveFormData = function (url,formData) {
        return $http.post(url, formData, {
        	transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
	};

	factory.delete = function (uri) {
		return  $http.delete(uri)
	};
    return factory;
    
});