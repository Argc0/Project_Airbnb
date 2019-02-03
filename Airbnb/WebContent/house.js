var app = angular.module('houseapp', []);

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


app.controller('HouseCtrl', ['$scope', '$http','fileUpload','$window', function($scope, $http, fileUpload, window) {
	var local = localStorage.getItem('userinfodata');
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	if(typeof local !== 'undefined' && local !== null){
		var token;
		if (typeof window.sessionStorage.token !== 'undefined' && window.sessionStorage.token !== null){
			token=window.sessionStorage.token;
		}else{
			token=varobj.token;
		}
		$scope.house= new Object();
		$scope.value_show1 = true;
		$scope.value_show2=false;
		$scope.value_show3=false;
		$scope.idhouse = -1;
		$scope.house.owner= varobj.username;
	
		var x=39.074208;
		var y=21.824311999999964;
		var mapProp= {
				center:new google.maps.LatLng(x, y),
				zoom:5,
		};
		var map=new google.maps.Map(document.getElementById("map"),mapProp);
	}else{
		alert("User does not exist!");
	}
    $scope.codeAddress=function(){
    	var geocoder = new google.maps.Geocoder();
    	var address = document.getElementById('address').value;
    	geocoder.geocode( { 'address': address}, function(results, status) {
    		$scope.res=results[0].geometry.location;
    		if (status == 'OK') {
    			map.setCenter(results[0].geometry.location);
    			var marker = new google.maps.Marker({
    				map: map,
    				position: results[0].geometry.location
    			});
    		} else {
    			alert('Geocode was not successful for the following reason: ' + status);
    		}
    		$scope.house.coord_x=$scope.res.lat();
    		$scope.house.coord_y=$scope.res.lng();
    		
    	});
    }
	
    $scope.next=function(){
    	$scope.value_show1=false;
    	$scope.value_show2=true;
    }
	$scope.insertHouse = function() {
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
	        method : "POST",
	        url : "services/house",
	        isArray: false,
	        data: $scope.house,
	        headers: customHead
	    }).success(function (data){
	    	if(data > 0){
	    		$scope.idhouse = data;
	    		$scope.value_show2 = false;
	    		$scope.value_show3=true;
	    		alert("Your house has been successfully inserted!");
	    	}else
	    		alert("The house hasn't been created!");
	    }).error(function (error, status){
	    	alert("Your session has finished!\nPlease login again!");
	    	window.location.href = '/Airbnb/login.html';
	    }); 
		
	};

    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        if($scope.idhouse!=-1){
        	var uploadUrl = "services/house/upload/" + $scope.idhouse;
        	fileUpload.uploadFileToUrl(file, uploadUrl);
        	alert("Your house image has been successfully inserted!");
        	window.location.href = '/Airbnb/welcomepage.html';
        }else{
        	alert("There no house to upload this photo!");
        }
     };
}]);


app.controller('HouseEditCtrl', ['$scope', '$http','$window', 'fileUpload', function($scope, $http, window, fileUpload) {
	var local =localStorage.getItem('userinfodata');
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	if(typeof local !== 'undefined' && local !== null){
		var token;
		if (typeof window.sessionStorage.token !== 'undefined' && window.sessionStorage.token !== null){
			token=window.sessionStorage.token;
		}else{
			token=varobj.token;
		}
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
			method : "GET",
			url : "services/house/list/" + varobj.username,
			headers: customHead
		}).success(function (data){
			if(data!=null)
				$scope.myHouses = data;
			else
				alert("No results for houses!");
		}).error(function (error, status){
			alert("Your session has finished!\nPlease login again!");
			window.location.href = '/Airbnb/login.html';
		}); 
	}else{
		alert("There is no owner to show the house!");
	}
	$scope.EditHouse=function(house){
		house.owner=varobj.username;
		localStorage.setItem('housedata', JSON.stringify(house));
		window.location.href = '/Airbnb/edit_house.html';
	}
}]);

app.controller('EditHouseCtrl', ['$scope', '$http', '$window', 'fileUpload', function($scope, $http, window, fileUpload) {
	$scope.house= new Object();
	$scope.value_show=true;
	$scope.res= new Object();
	var local1=localStorage.getItem('housedata');
	var local2=localStorage.getItem('userinfodata');
	var housedata = JSON.parse(localStorage.getItem('housedata'));
	$scope.apo=housedata;
	var varobj = JSON.parse(localStorage.getItem('userinfodata'));
	if(typeof local1 !== 'undefined' && local1!==null && typeof local2 !== 'undefined' && local2!==null){
		var token;
		if (typeof window.sessionStorage.token !== 'undefined' && window.sessionStorage.token !== null){
			token=window.sessionStorage.token;
		}else{
			token=varobj.token;
		}
		var map;
		$http.get("services/house/"+ housedata.idHouse)
		.then(function(response) {
			$scope.house = response.data;
			var x=$scope.house.coord_x;
			var y=$scope.house.coord_y;
			var mapProp= {
	      		center:new google.maps.LatLng(x, y),
	      		zoom:5,
			};
			map=new google.maps.Map(document.getElementById("map"),mapProp);
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng(x,y),
				map: map
			});
		});
	}else{
		alert("There are no data for editing the house!");
	}
	$scope.codeAddress=function(){
    	var geocoder = new google.maps.Geocoder();
    	var address = document.getElementById('address').value;
    	geocoder.geocode( { 'address': address}, function(results, status) {
    		$scope.res=results[0].geometry.location;
    		if (status == 'OK') {
    			map.setCenter(results[0].geometry.location);
    			var marker = new google.maps.Marker({
    				map: map,
    				position: results[0].geometry.location
    			});
    		} else {
    			alert('Geocode was not successful for the following reason: ' + status);
    		}
    		$scope.house.coord_x=$scope.res.lat();
    		$scope.house.coord_y=$scope.res.lng();
    		
    	});
    }
	$scope.UpdateHouse= function (){
		var customHead = {Authorization: 'Bearer ' + token};
		$http({
	        method : "PUT",
	        url : "services/house/update",
	        isArray: false,
	        data: $scope.house,
	        headers: customHead
	    }).success(function (data){
	    	$scope.value_show=false;
	    	alert("Changes have been saved!");
	    }).error(function (error, status){
	    	alert("Your session has finished!\nPlease login again!");
	    	window.location.href = '/Airbnb/login.html';
	    }); 
	}
	 $scope.uploadFile = function(){
	        var file = $scope.myFile;
	        var uploadUrl = "services/house/upload/" + housedata.idHouse;
	        fileUpload.uploadFileToUrl(file, uploadUrl);
	        alert("Your house has been successfully inserted!");
	        window.location.href = '/Airbnb/welcomepage.html';
	     };
	
}]);


app.controller('PresentationHouseCtrl', ['$scope', '$http','$window', 'fileUpload', function($scope, $http, window, fileUpload) {
	var local =localStorage.getItem('idhouse');
	var varobj = JSON.parse(localStorage.getItem('idhouse'));
	$scope.host_inf=false;
	$scope.host_show=function(){
		$scope.host_inf=true;
	}
	if(typeof local !== 'undefined' && local!==null){
		$scope.id=varobj;
		$http.get("services/house/" + $scope.id)
		.then(function(response) {
			$scope.house = response.data;
			var x=$scope.house.coord_x;
			var y=$scope.house.coord_y;
			var mapProp= {
				  center:new google.maps.LatLng(x, y),
				  zoom:5,
			};
			var map=new google.maps.Map(document.getElementById("map"),mapProp);
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng(x,y),
				map: map
			});
		});
	}else{
		alert("There is no id for house!");
	}
	$scope.send = function(){
		var owner= $scope.house.owner;
		localStorage.setItem('messageinfo', JSON.stringify(owner));
		window.location.href = '/Airbnb/send_message.html';
	};
	$scope.reservation=function(){
		var local1 = localStorage.getItem('search');
		var local2 = localStorage.getItem('idhouse');
		var local3 = localStorage.getItem('userinfodata');
		if(typeof local1 !== 'undefined' && local1!==null && typeof local2 !== 'undefined' && local2!==null && typeof local3 !== 'undefined' && local3!==null){
			var search = JSON.parse(localStorage.getItem('search'));
			var id = JSON.parse(localStorage.getItem('idhouse'));
			var user = JSON.parse(localStorage.getItem('userinfodata'));
			$scope.reservationInfo=new Object();
			$scope.reservationInfo.datefrom=search.date_from;
			$scope.reservationInfo.dateto=search.date_to;
			$scope.reservationInfo.idhouse=id;
			$scope.reservationInfo.username=user.username;
			if(search.date_from != null && search.date_to != null){
				alert("Reservation dates: " + search.date_from + " to " + search.date_to);
				$http.post("services/house/reservation", $scope.reservationInfo)
				.then(function(response) {
					if(response!=-1)
						alert("Reservation has been done!");
					else
						alert("Error ocured with the reservation!");
				});
			}else{
				alert("There ar no dates set for reservation!\nPlease select dates!");
			}
		}else{
			alert("There are no data to make the reservation!");
		}
	};
	
	$scope.review = function(){
		$scope.reviewInfo= new Object();
		$scope.reviewInfo.text=$scope.text;
		$scope.reviewInfo.forhost=$scope.forhost;
		var id = JSON.parse(localStorage.getItem('idhouse'));
		$scope.reviewInfo.idhouse=id;
		var local=localStorage.getItem('userinfodata');
		if(typeof local !== 'undefined' && local !== null){
			var user = JSON.parse(localStorage.getItem('userinfodata'));
			$scope.reviewInfo.username=user.username;
			$scope.date= new Date();
			$scope.reviewInfo.date=$scope.date;
			$http.post("services/house/review", $scope.reviewInfo)
			.then(function(response) {
				if(response.data=="true")
					alert("Review has been submited!");
				else
					alert("You can not post a review!");
			});
		}else{
			alert("You have to login to make a review!");
		}
	};
	$scope.Rating = function(){
		$scope.rateInfo= new Object();
		var id = JSON.parse(localStorage.getItem('idhouse'));
		$scope.rateInfo.idhouse=id;
		var local=localStorage.getItem('userinfodata');
		if(typeof local !== 'undefined' && local !== null){
			var user = JSON.parse(localStorage.getItem('userinfodata'));
			$scope.rateInfo.username=user.username;
			$scope.date= new Date();
			$scope.rateInfo.date=$scope.date;
			$scope.rateInfo.rate=$scope.rate;
			$http.post("services/house/rating", $scope.rateInfo)
			.then(function(response) {
				if(response.data=="true")
					alert("Rate has been submited!");
				else
					alert("You can not rate this house!");
			});
		}else{
			alert("You have to login to make a rate!");
		}
	};
	
}]);