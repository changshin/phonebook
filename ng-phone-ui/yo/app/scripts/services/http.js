angular.module('ngphoneUIApp').service("HttpInterceptor",function($q,$rootScope,$window, $location ){
	this.request = function(config){

		if  (  config.url.indexOf("api/users/logout") < 0
			&& config.url.indexOf("common/json") < 0 ) {
			$rootScope.showLoader++;
    	}
		//console.log("config.url.search="+config.url.search);
	    //console.log("config.url="+config.url);
	    //console.log("config.method="+config.method);
		if ( config.url.search("googleapis.com")!= -1 ) {	// for google api
			// skip, nothing
		} else  {
			//config.headers["Language"] = $window.localStorage.language;
			config.headers["Language"] = $rootScope.languageSelected;
			if(!config.cache && config.url.search("/api/")!= -1){
			      config.url = $rootScope.baseURL + config.url;
			      //console.log("config.url="+config.url);
			      if ($rootScope.user) {
			    	  config.headers["Authorization"] = $rootScope.user.accessToken;
				  }
			      if (config.url.search("\\?")== -1){
			    	  config.url = config.url + "?tm="+  new Date().getTime() ;
			      } else {
			    	  config.url = config.url + "&tm="+  new Date().getTime() ;
			      }
			}
	    }
	    return config;
	}	// end of this.request = function(config){
	
	this.requestError = function(rejection) {
	    // do something on error
	    $rootScope.showLoader--;
	    return $q.reject(rejection);
    },
	
	this.response = function(response){
    	// not show loader exception
		//console.log("response.config.url="+response.config.url);
		if  (   response.config.url.indexOf("api/users/logout") < 0
			&& response.config.url.indexOf("common/json") < 0 ) {
            $rootScope.showLoader--;
    	}
	    if (response && response.data.status == 'fail') {
	    	//console.log(response.data);
	    	var error_message =  response.data.err_msg;
	    	$rootScope.$broadcast('broadcast_error',error_message);
	    	if (response.data.exception_type == "Authentication Exception" ){
	    		delete $rootScope.user;
	    		delete $window.localStorage.user;
	    		$window.localStorage.clear();
	    		$rootScope.user = "";
	    		$rootScope.isAuthenticated = false;
		    	$rootScope.$broadcast('user_loggout');
	    		$rootScope.toBeReloaded = true;
	    		//$window.location.hash = "#/login";
	    		$location.path("/login");
	    	}else if(response.data.exception_type == "FS1002"){
	    		$rootScope.$broadcast('broadcast_error', $rootScope.alertMessages.SERVICES_UNKNOWN_EXCEPTION_SERVER_SIDE_ERROR);
	    	}
	    } 
		return response || $q.when(response);
		
	}	//  end of this.response = function(response){
	
	this.responseError = function (response) {
        $rootScope.showLoader--;
		var title = "Error:";
		var msg = "";
		if (response && response.data) {	
			if(response.status == 404){
				$rootScope.$broadcast('broadcast_error',response.status + ' '+ response.data.error);
			}else{
				title = "Error Status: " + response.status;
				var error_message =  response.data.err_msg;
		    	$rootScope.$broadcast('broadcast_error',error_message);
			}
		}
		return $q.reject(response);
	}
});