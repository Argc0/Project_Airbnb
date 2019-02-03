'use strict';
var app = angular.module('userapp', ['angularUtils.directives.dirPagination']);

app.directive('ngUnique', function($http) {
	return {
		restrict: 'AE',
		require: 'ngModel',
		link: function(scope, element, attrs, ngModel) { 
	    	element.bind('blur', function (e) {
	    		if (!ngModel || !element.val()) return;
	  	        var currentValue = element.val();
	  	        $http.get('services/users/check_username/' + currentValue)
	  	        .success(function(response){
	  	        	if(response=="true"){
	  	        		ngModel.$setValidity('unique', false);
	  	        	}else{
	  	        		ngModel.$setValidity('unique', true);
	  	        	}
	  	        });
	        	  
	    	});
	    }
	} 
});

app.directive('fileModel', ['$parse', function ($parse) {
   return {
      restrict: 'A',
      link: function(scope, element, attrs) {
         var model = $parse(attrs.fileModel);
         var modelSetter = model.assign;
         
         element.bind('change', function(){
            scope.$apply(function(){
               modelSetter(scope, element[0].files[0]);
            });
         });
      }
   };
}]);

app.service('fileUpload', ['$http', function ($http) {
   this.uploadFileToUrl = function(file, uploadUrl){
      var fd = new FormData();
      fd.append('file', file);
   
      $http.post(uploadUrl, fd, {
         transformRequest: angular.identity,
         headers: {'Content-Type': undefined}
      })
   
      .success(function(){
      })
   
      .error(function(){
      });
   }
}]);


app.controller('UserCtrl', ['$scope', '$http','fileUpload', '$window', function($scope, $http, fileUpload, window) {
	$scope.user = new Object();
	$scope.logininfo = new Object();
	$scope.theFile = new Object();
	$scope.photo = "photo"
	$scope.value_show=true;
	$scope.iduser=-1;
	
	
	$scope.error = false;
	$scope.incomplete = true;
	
	
	var local=localStorage.getItem('userinfodata');
	if (typeof local !== 'undefined' && local !== null){
		var varobj = JSON.parse(localStorage.getItem('userinfodata'));
		$scope.login_bool=true;
		if((varobj.isApproved==true)&&(varobj.isHost==true)){
			$scope.host_bool=true;
		}else{
			$scope.host_bool=false;
		}
		if(varobj.isAdmin==true){
			$scope.admin_bool=true;
		}else{
			$scope.admin_bool=false;
		}
	}else{
		$scope.login_bool=false;
	}
	
	$scope.user.ishost=false;
	$scope.user.istenant=false;
    $scope.user.firstName = '';
    $scope.user.lastName = '';
	
	$scope.$watch('user.password',function() {$scope.test();});
	$scope.$watch('repeat_password',function() {$scope.test();});
	$scope.$watch('user.firstName', function() {$scope.test();});
	$scope.$watch('user.lastName', function() {$scope.test();});
	$scope.$watch('user.ishost' , function() {$scope.test();});
	$scope.$watch('user.istenant' , function() {$scope.test();});

	$scope.test = function() {
	  if ($scope.user.password !== $scope.repeat_password) {
	    $scope.error = true;
	    } else {
	    $scope.error = false;
	  }
	  $scope.incomplete = false;
	  if (!$scope.user.firstName.length ||
	  !$scope.user.lastName.length ||
	  !$scope.user.password.length || !$scope.repeat_password.length) {
	     $scope.incomplete = true;
	  }
	  $scope.check = false;
	  if(($scope.user.ishost == false) && ($scope.user.istenant==false)){
		  $scope.check = true;
	  }
	};	
	
	$scope.logout = function () {
        localStorage.clear();
        window.location.href = '/Airbnb/welcomepage.html';
    };
	
	$scope.login = function() {
		$http
        .post('services/users/login', $scope.logininfo)
        .success(function(response){
        	if(response.isApproved==false){
        		if(response.isTenant==true){ 
        			alert("You haven't been approved for host yet!\nBut you can continue as tenant!");
        			window.sessionStorage.token = response.token;
                	$scope.resprint = response.token;
                	localStorage.setItem('userinfodata', JSON.stringify(response));
        		}
        		else
        			alert("You haven't been approved yet!\nPlease login some other time!");
        	}else{
        		window.sessionStorage.token = response.token;
            	$scope.resprint = response.token;
            	localStorage.setItem('userinfodata', JSON.stringify(response));
        		if(response.isAdmin == true) {
        			window.location.href = '/Airbnb/adminpage.html';
        		}
        		else {
        			window.location.href = '/Airbnb/welcomepage.html';
        		}
        	}
        }).error(function (error, status){
	    	alert("There is not a user with these values!");
	    }); 
	};
	
	$scope.insertUser = function() {
		if($scope.error || $scope.incomplete || $scope.check){
			if($scope.check == true){
				alert("One of the check boxes must be selected!")
			}
			if($scope.error==true){
				alert("Password and repeat-password must be the same!")
			}
			
		}else{		
			$http.post("services/users/signup", $scope.user)
			.then(function (response) {
				if(response.dat==-1){
					alert("Problem with signup!");
				}else{
					$scope.value_show=false;
					$scope.iduser=response.data;
					if($scope.user.ishost==true){
			    		alert("Your request has been submitted!\nWe will let you know when is approved!");
			    	}
			    	else{
			    		alert("Congratulations!\nYou have been successfully signed up in Airbnb!");
			    	}
				}
			});
		}
    };
    
    $scope.skip = function(){
    	if($scope.user.ishost==true){
    		alert("Your request has been submitted!\nWe will let you know when is approved!");
    	}
    	else{
    		alert("Congratulations!\nYou have been successfully signed up in Airbnb!");
    	}
    	window.location.href = '/Airbnb/welcomepage.html';
    };
    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        if($scope.iduser!=-1){
        	var uploadUrl = "services/users/upload/" + $scope.iduser;
        	fileUpload.uploadFileToUrl(file, uploadUrl);
        	$scope.skip();
        }else{
        	alert("Something went wrong with the id of the user!")
        }
     };
    
}]);

app.controller('IndexCtrl', ['$scope','$window', function($scope, window) {
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	$scope.user = varobj;
	$scope.isapproved= varobj.isApproved;
	var local=localStorage.getItem('userinfodata');
	if (typeof local !== 'undefined' && local !== null){
		$scope.login_bool=true;
	}else{
		$scope.login_bool=false;
	}
	$scope.logout = function () {
        localStorage.clear();
        window.location.href = '/Airbnb/welcomepage.html';
    };
}]);

app.controller('SearchCtrl', ['$scope','$http', function($scope, $http) {
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	$scope.show_search=false;
	$scope.filter=false;
	$scope.search= new Object();
	$scope.mySHouses= new Object();
	
	$scope.filters=function(){
		$scope.filter=true;
	}
	$scope.searching=function(){
		$scope.show_search=false;
	}
	$scope.search_form=function() {
		 if (($scope.search.country == null || $scope.search.country=='' ) && ($scope.search.city == null || $scope.search.city=='') &&
			(($scope.search.date_from == null) || ($scope.search.date_from == null)) && 
			($scope.search.address == null || $scope.search.address=='') && ($scope.search.num_guests == null || $scope.search.num_guests=='')) {
				     alert("You have to fill at least one field!");
		}else{
			if(($scope.search.date_from < $scope.search.date_to) || $scope.search.date_from==null){
				var local=localStorage.getItem('userinfodata');
				if (typeof local !== 'undefined' && local !== null){
					var varobj = JSON.parse(localStorage.getItem('userinfodata'));
					$scope.search.name=varobj.username;
				}else{
					$scope.search.name=null;
				}
				$http
				.post('services/house/search', $scope.search)
				.success(function(response){
					if(response!=-1){
						$scope.show_search=true;
						$scope.mySHouses=response;
					}else
						alert("No results found!");		
				});
			}
			else
				alert("Date from must be before date to!");
		}
	};
	
	$scope.pageChangeHandler = function(num) {
	      console.log('meals page changed to ' + num);
	  };
	  
	 $scope.house_details= function(house) {
		 localStorage.setItem('idhouse', JSON.stringify(house.idHouse));
		 localStorage.setItem('search', JSON.stringify($scope.search));
		 window.location.href = '/Airbnb/presentation_house.html';
	 };
}]);

app.controller('AdminCtrl', ['$scope','$http', '$window', function($scope, $http, window) {
	$scope.mytemp = -1;
	$scope.myUser= new Object();
	var token;
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	if (typeof window.sessionStorage.token !== 'undefined' && window.sessionStorage.token !== null){
		token=window.sessionStorage.token;
	}else{
		token=varobj.token;
	}
	if (typeof token !== 'undefined' && token !== null){
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
        	method : "GET",
        	url : "services/users/admin",
        	headers: customHead
    	}).success(function (data){
    		if(data!=null)
    			$scope.myUsers = data;
    		else
    			alert("Error ocurred on loading users!")
    	}).error(function (error, status){
    		alert("Your session has finished!\nPlease login again!");
    		window.location.href = '/Airbnb/login.html';
    	}); 
	}else{
		alert("You don't have authorization for admin!");
		window.location.href = '/Airbnb/login.html';
	}
	
	$scope.export_xml = function() {
			var customHead = {Authorization: 'Bearer ' + token};
			$http({
				method : "GET",
				url : "services/users/admin/xml",
				responseType: "arraybuffer",
				headers: customHead
			}).success(function (data){
				if(data==-1){
					alert("Some error has occured with the creations of xml files!")
				}else{
					var blob = new Blob([data], {type: 'application/zip'});
					saveAs(blob, "xml.zip");
				}
			}).error(function (error, status){
				alert("Your session has finished!\nPlease login again!");
				window.location.href = '/Airbnb/login.html';
			}); 
	};
	
	$scope.edit = function(user) {
		$scope.mytemp = user.idUser;
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
	        method : "GET",
	        url : "services/users/admin/" + user.username,
	        headers: customHead
	    }).success(function (data){
	    	if(data!=null){
	    		$scope.myUser = data;
	    		localStorage.setItem('userdata', JSON.stringify(data));
	    		window.location.href = '/Airbnb/edit_user.html';
	    	}
	    	else
	    		alert("Error occured loading user!");
	    }).error(function (error, status){
	    	alert("Your session has finished!\nPlease login again!");
	    	window.location.href = '/Airbnb/login.html';
	    }); 
	};
	
	
}]);

app.controller('EditUserCtrl', ['$scope','$http', function($scope, $http) {
	$scope.myuser= new Object();
	var local=localStorage.getItem('userdata');
	var varobj = JSON.parse(localStorage.getItem('userdata'));
	$scope.myuser = varobj;
	if (typeof local == 'undefined' || local == null){
		alert("There is no such a user!");
	}
	var token;
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	if (typeof window.sessionStorage.token !== 'undefined' && window.sessionStorage.token !== null){
		token=window.sessionStorage.token;
	}else{
		token=varobj.token;
	}
	$scope.approve = function() {
		if (typeof token !== 'undefined' && token !== null){
			var customHead = {Authorization: 'Bearer ' + token};
			$http({
				method : "PUT",
				url : "services/users/admin/" + $scope.myuser.idUser,
				headers: customHead
			}).success(function (data){
				if(data!=-1){
					$scope.myuser = data;
					alert("The user has been approved!");
					window.location.href = '/Airbnb/adminpage.html';
				}else
					alert("Error occured when user is been approved!");
			}).error(function (error, status){
				alert("Your session has finished!\nPlease login again!");
				window.location.href = '/Airbnb/login.html';
			}); 
		}else{
			alert("You have to login as admin");
			window.location.href = '/Airbnb/login.html';
		}
	};
}]);


app.controller('EditProfileCtrl', ['$scope','$http', '$window', 'fileUpload', function($scope, $http, window, fileUpload) {
	$scope.photohide = true;
	$scope.userinfo= new Object();
	var local=localStorage.getItem('userinfodata');
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	$scope.userinfo = varobj;
	$scope.userdata = new Object();
	$scope.userdata.idUser=-1;
	$scope.logininfo = new Object();
	if (typeof local !== 'undefined' && local !== null){
		var token;
		if (typeof window.sessionStorage.token !== 'undefined' && window.sessionStorage.token !== null){
			token=window.sessionStorage.token;
		}else{
			token=varobj.token;
		}
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
			method : "GET",
			url : "services/users/admin/" + $scope.userinfo.username,
			headers: customHead
		}).success(function (data){
			if(data!=null)
				$scope.userdata = data;
			else
				alert("Error occured loading user!");
		}).error(function (error, status){
			alert("Your session has finished!\nPlease login again!");
			window.location.href = '/Airbnb/login.html';
		}); 
	}else{
		alert("There is no user to load!");
	}
	$scope.EditUser = function() {
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
	        method : "PUT",
	        url : "services/users/edit",
	        isArray: false,
	        data: $scope.userdata,
	        headers: customHead
	    }).success(function (data){
	    	var newusername = $scope.userdata.username;
	    	varobj.username = newusername;
	    	localStorage.setItem('userinfodata', JSON.stringify(varobj));
	    	$scope.logininfo = varobj;
	    	alert("Changes saved successfully!");
	    	window.locaton.href = '/Airbnb/edit-profile.html'
	    }).error(function (error, status){
	    	alert("Your session has finished!\nPlease login again!");
	    	window.location.href = '/Airbnb/login.html';
	    }); 
	};
	
	$scope.ChangePhoto = function () {
		$scope.photohide = false;
	}
	
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        if($scope.userdata.idUser!=-1){
        	var uploadUrl = "services/users/upload/" + $scope.userdata.idUser;
        	fileUpload.uploadFileToUrl(file, uploadUrl);
        	alert("Photo changed successfully!");
        	window.location.href = '/Airbnb/edit-profile.html';
        }else{
        	alert("There is no user to upload the photo!");
        }
     };
}]);

app.controller('ChangePwdCtrl', ['$scope','$http', '$window', function($scope, $http, window) {
	var local = localStorage.getItem('userinfodata');
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	$scope.logininfo = new Object();
	if (typeof local == 'undefined' || local == null){
		alert("Something is wrong!\nThere is no user to change password!");
	}
	$scope.ChangePwd = function() {
		if ($scope.oldpassword == varobj.password) {
			if ($scope.newpassword == $scope.confirmpassword) {
				$http.put("services/users/change-pwd/" + varobj.username, $scope.newpassword)
				  .then(function(response) {
					  if(response.data!=-1){
						  var newpassword = $scope.newpassword;
						  varobj.password = newpassword;
						  localStorage.setItem('userinfodata', JSON.stringify(varobj));
						  $scope.logininfo = varobj;  
						  alert("Changes saved successfully!");
						  window.location.href = '/Airbnb/edit-profile.html';
					  }else{
						  alert("Something wrong when changing password!");
					  }
				  });
			}
			else {
				alert("Passwords must agree. Try again!");
			}
		}
		else {
			alert("Old password is wrong. Try Again!");
		}
	};
}]);

app.controller('MessageCtrl', ['$scope','$http', function($scope, $http) {
	$scope.mymessage="Write your message...";
	$scope.info=new Object();
	$scope.Send= function(){
		var local1= localStorage.getItem('messageinfo');
		var local2=localStorage.getItem('userinfodata');
		var owner = JSON.parse(localStorage.getItem('messageinfo'));
		var varobj = JSON.parse(localStorage.getItem('userinfodata'));
		if (typeof local1 !== 'undefined' && local1 !== null && typeof local2 !== 'undefined' && local2 !== null ){
			$scope.info.host_name=owner;
			$scope.info.tenant_name=varobj.username;
			$scope.info.text=$scope.mymessage;
			$http.post("services/users/send_message", $scope.info)
			.then(function(response) {
					$scope.mydata=response.data;
					alert("Your message has been sent!");
					window.history.go(-1);
			});
		}else{
			alert("The message can't be send");
		}
	}
	$scope.history=function(){
		window.history.go(-1);
	}
	
}]);

app.controller('MessageListCtrl', ['$scope','$http', function($scope, $http) {
	var local=localStorage.getItem('userinfodata');
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	var token;
	if(typeof local !== 'undefined' && local !== null){
		if (typeof window.sessionStorage.token !== 'undefined' && window.sessionStorage.token !== null){
			token=window.sessionStorage.token;
		}else{
			token=varobj.token;
		}
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
			method : "GET",
			url : "services/users/list_messages/" + varobj.username,
			headers: customHead
		}).success(function (data){
			if(data!=null){
				$scope.messages = data;
			}else{
				alert("There are no messages!")
			}
		}).error(function (error, status){
			alert("Your session has finished!\nPlease login again!");
			window.location.href = '/Airbnb/login.html';
		});
	}else{
		alert("There is no user to find messages!");
	}
	$scope.send=function(name){
		localStorage.setItem('messageinfo', JSON.stringify(name));
		window.location.href = '/Airbnb/send_message.html';
	}
	$scope.delmessage=function(message){
		$http.put("services/users/delete_message" ,message )
		  .then(function(response) {
			  if(response.data!=-1){
				  $scope.mess = response.data;
			  }else{
				  alert("Smoething went wrong!");
			  }
		  });
		alert("The message has been deleted!")
		window.location.reload();
	}
	
}]);