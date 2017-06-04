'use strict';

/**
 * @ngdoc function
 * @name ngphoneUIApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the ngphoneUIApp
 */
angular.module('ngphoneUIApp').controller('MainCtrl', 
function($scope, $window, $rootScope, $location, $http, $timeout, dbFactory,$modal, $anchorScroll,$translate, $route, $filter) {

	//to force to start from top page after other page scrolling 
 	$anchorScroll();

 	$scope.alerts = [];
    $scope.loggedIn = false;

    
    $scope.header_view = "views/header.html";
    $scope.locationPath = $location.path();
    
    $scope.languageSelected = $window.localStorage.language;
	$rootScope.languageSelected = $scope.languageSelected;
	$translate.use($scope.languageSelected);
	if  (  $scope.languageSelected == undefined ) {
		$scope.languageSelected = "en";
	}
	$scope.languageSelected = "en";
	var jsonFile = $scope.languageSelected + "-messages.json";
	$http.get('common/json/' + jsonFile)
  		.then(function(response){
  			localStorage.setItem("alertMsg",angular.toJson(response.data));
  			$rootScope.alertMessages = JSON.parse(localStorage.getItem("alertMsg")).messages;
  		},
  		function(response){
  			console.log(response);
  	});
	
	
    
    //method to get logged in user Avatar path.
    var getAvatar = function() {
        //var timestamp = new Date().getTime();
       if ($rootScope.user != undefined && $rootScope.user != '') {
            return $rootScope.baseURL + '/' + $rootScope.user.avatarPath; // + '?' + timestamp;  
        } else {
            return "/images/default_avatar.png";
        }
    };

    //$rootScope.avatarPath = getAvatar();

   
    
    $scope.$on('update_avatar', function(event, data) {
    	getAvatar();
    });
    
    //Broadcasting close message.
    $scope.$on('broadcast_closeMessage', function(event) {
        event.currentScope.closeAlert();

    });
  
    //Broadcasting success message.
    $scope.$on('broadcast_success', function(event, data) {
        $scope.alert = {
            type: 'success',
            msg: data
        };
    });

    //Broadcasting error message.
    $scope.$on('broadcast_error', function(event, data) {
        $scope.alert = { type: 'danger', msg: data  };
    });

   
    //alert close method.
    $scope.closeAlert = function() {
        $scope.alert = null;
    };

    $scope.closeAlert2 = function() {
        $scope.alert2 = null;
    };
    
    $scope.$on('$routeChangeSuccess', function(scope) {

        $scope.$on('$locationChangeStart',function(evt, absNewUrl, absOldUrl) {
            //console.log(absNewUrl+", "+absOldUrl);
        });
        //var hash = $window.location.hash; 
           
    });	// end of $scope.$on('$routeChangeSuccess', function(scope) {

    $scope.$on('set_language', function(event, language) {
    	if  ( language != undefined ) {
            $scope.changeLanguage(language);
    	} else {
            $scope.changeLanguage($scope.languageSelected);
    	}
    });
    
    $scope.$on('user_loggout', function() {
    	$scope.loggedIn = false;
        $window.localStorage.loggedIn = false;
    });

    $scope.$on('user_login', function() {
    	console.log("user_login is called");
    	$scope.loggedIn = true;
        $window.localStorage.loggedIn = true;

    });



    //user logout method.
    $scope.logout = function() {
       	$scope.loggedIn = false;
        delete $window.localStorage.loggedIn;
        $window.localStorage.clear();
        $rootScope.$broadcast("user_loggout");
        $location.path("/login");
 
    }
	
	$scope.changeLanguage = function(language) {
		if  ( language == undefined ) {
			language = "en";
		}
		$scope.languageSelected = language;
		$rootScope.languageSelected = $scope.languageSelected;
		$window.localStorage.language = language;
		$translate.use($rootScope.languageSelected);
  	   	var jsonFile = $rootScope.languageSelected + "-messages.json";
  	   	$http.get('common/json/' + jsonFile)
	  		.success(function(response){
	  			localStorage.setItem("alertMsg",angular.toJson(response));
	  			$rootScope.alertMessages = JSON.parse(localStorage.getItem("alertMsg")).messages;
	  		})
	  		.error(function(response){
	  			console.log(response);
	  		});
  	   	$route.reload();	// to change the latest one
	}

})