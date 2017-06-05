'use strict';

/**
 * @ngdoc overview
 * @name ngphoneUIApp
 * @description # ngphoneUIApp
 * 
 * Main module of the application.
 */
angular.module(	'ngphoneUIApp',	[ 'ngAnimate', 'ngCookies','ngRoute', 'ngSanitize',
		  'ngTouch', 'ui.bootstrap','pascalprecht.translate','ngMessages','chart.js'],
		[ '$translateProvider', function($translateProvider,$window) {
			$translateProvider.useSanitizeValueStrategy(null);
			$translateProvider.useStaticFilesLoader({
				prefix : '/common/json/',
				suffix : '.json'
			});
			$translateProvider.preferredLanguage('en');
		} ])
		.config( function($routeProvider, $httpProvider, ChartJsProvider, $locationProvider) {
			$locationProvider.hashPrefix('');
			ChartJsProvider.setOptions({ 
				colors : [ '#803690', '#00ADF9', '#DCDCDC', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
				responsive : true
			});
			
			$httpProvider.interceptors.push('HttpInterceptor');

			$routeProvider.when('/', {
				templateUrl : 'views/main.html',
				controller : 'MainCtrl'
			}).when('/users', {
				templateUrl : 'views/users.html',
				controller : 'UserCtrl'
			}).when('/login', {
				templateUrl : 'views/login.html',
				controller : 'loginCtrl'
			}).when('/groups', {
				templateUrl : 'views/groups.html',
				controller : 'groupsCtrl'
			}).when('/faq', {
				templateUrl : 'views/faq.html',
				controller : 'MainCtrl'
			}).otherwise({			
				redirectTo : '/'
			});
		})
		.run([ '$templateCache', '$rootScope', '$http', '$route', '$window','$location',
			function($templateCache, $rootScope, $http, $route, $window, $location) {
			
			
				if (!window.location.origin) { // ie10 does not support
					// window.location.origin
					window.location.origin = window.location.protocol + "//"
							+ window.location.hostname	+ (window.location.port ? ':' + window.location.port : '');
				}

				$rootScope.showLoader = 0;
				$rootScope.baseURL = window.location.origin;

				if ($window.location.port == "9000") {
					$rootScope.baseURL = $window.location.protocol + "//" + $window.location.hostname + ":8080";
				}
				$rootScope.isAuthenticated = false;
				$rootScope.headerColor = '';

				if ($window.localStorage.users) {
					$rootScope.user = JSON.parse($window.localStorage.users);
				}
				if ($rootScope.user) {
					$rootScope.isAuthenticated = true;
				}

			}
		]);