'use strict';

/**
 * @ngdoc function
 * @name ngphoneUIApp.controller:loginCtrl
 * @description # loginCtrl Controller of the ngphoneUIApp
 */
angular.module('ngphoneUIApp').controller('loginCtrl',
    function($scope, $translate, $rootScope, $location, $window, $anchorScroll, $filter) {

		var $translate = $filter('translate');
 		$anchorScroll();
		
        $scope.userDetails = {
            email: "",
            password: "",
            browserName: $window.navigator.userAgent,
            platformName: $window.navigator.platform
        };
       
        $scope.loginUser = function(form) {
            if (form.$invalid) {
                form.invalid = true;
                return;
            }

            console.log(form);
            console.log($scope.userDetails);
            $rootScope.$broadcast('user_login');
            $location.path("/users");
        }

    });
