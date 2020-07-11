/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"usestrict";



//create a new module, and load the other pluggable modules 
var module = angular.module('GrantApp', ['ngResource','ngStorage'])

module.factory('evaluatorSignInDAO',function($resource) {
    return $resource('/api/evaluator/:username');
});

module.factory('applicantSignInDAO',function($resource) {
    return $resource('/api/applicant/:username');
});

module.factory('grantDAO', function ($resource) {
    return $resource('/api/grants/');
});

module.factory('departmentDAO', function ($resource) {
    return $resource('/api/departments/:dep');
});

module.factory('applicationDAO', function ($resource){
    return $resource('api/application_form');
});

module.factory('applicationByGrantDAO', function($resource){
    return $resource('api/application_form/:grantId');
});

module.factory('evaluationDAO', function($resource){
    return $resource('api/receipts');
});


module.factory('proposalDAO', function ($resource){
    return $resource('api/proposal/:applicationId');
});

module.controller('ProposalController',function($sessionStorage, $window, proposalDAO, $http) {
    base64str = null;
    //test = "JVBERi0xLjQKJeLjz9MKMiAwIG9iago8PC9MZW5ndGggNjUvRmlsdGVyL0ZsYXRlRGVjb2RlPj5zdHJlYW0KeJwr5HIK4TI2U7AwMFMISeEyUNA1tAAx9N0MFQyNFELSuDQyUnNy8hXK84tyUhQ1Q7KAagxAKlxDuAK5AM2xDvAKZW5kc3RyZWFtCmVuZG9iago0IDAgb2JqCjw8L1R5cGUvUGFnZS9NZWRpYUJveFswIDAgNTk1IDg0Ml0vUmVzb3VyY2VzPDwvRm9udDw8L0YxIDEgMCBSPj4+Pi9Db250ZW50cyAyIDAgUi9QYXJlbnQgMyAwIFI+PgplbmRvYmoKMSAwIG9iago8PC9UeXBlL0ZvbnQvU3VidHlwZS9UeXBlMS9CYXNlRm9udC9IZWx2ZXRpY2EvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjMgMCBvYmoKPDwvVHlwZS9QYWdlcy9Db3VudCAxL0tpZHNbNCAwIFJdPj4KZW5kb2JqCjUgMCBvYmoKPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDMgMCBSPj4KZW5kb2JqCjYgMCBvYmoKPDwvUHJvZHVjZXIoaVRleHSuIDUuNS4xMy4xIKkyMDAwLTIwMTkgaVRleHQgR3JvdXAgTlYgXChBR1BMLXZlcnNpb25cKSkvQ3JlYXRpb25EYXRlKEQ6MjAyMDA0MTAxNTIzMDQrMTInMDAnKS9Nb2REYXRlKEQ6MjAyMDA0MTAxNTIzMDQrMTInMDAnKT4+CmVuZG9iagp4cmVmCjAgNwowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDAyNTggMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAwMzQ2IDAwMDAwIG4gCjAwMDAwMDAxNDYgMDAwMDAgbiAKMDAwMDAwMDM5NyAwMDAwMCBuIAowMDAwMDAwNDQyIDAwMDAwIG4gCnRyYWlsZXIKPDwvU2l6ZSA3L1Jvb3QgNSAwIFIvSW5mbyA2IDAgUi9JRCBbPGRiNmQxNWJhOTQ1M2YwMmI3ZGU0MjdiODFiMzQxY2FlPjxkYjZkMTViYTk0NTNmMDJiN2RlNDI3YjgxYjM0MWNhZT5dPj4KJWlUZXh0LTUuNS4xMy4xCnN0YXJ0eHJlZgo2MDIKJSVFT0YK";
 
    this.upload = function() {
        //console.log("application id = " + $sessionStorage.appId);
        var input = document.getElementById('file').files[0];
        reader = new FileReader();
        reader.readAsDataURL(input);
        
        reader.onload = function () {
            console.log($sessionStorage.appId)
            var encodedProposal = reader.result.split(',')[1];    
            proposalDAO.save({'applicationId': $sessionStorage.uploadAppId, 'encodedProposal': encodedProposal});
            // if saved
            var message = document.createElement("div");
            message.setAttribute("id", "missingProposal"); 
            var text = document.createTextNode("Thank you, your proposal has been submitted!");
            message.appendChild(text);
            document.body.appendChild(message);
            
        }
    }
    
    // select applicationid before upload of proposal
    this.selectApplicationProposalUpload = function(id) {
        $sessionStorage.uploadAppId = id;
        $window.open('upload-proposal.html');
    }
    
    // select applicationid before viewing of proposal
    this.selectApplicationProposalView = function(id) {
        $sessionStorage.viewAppId = id;
        $window.open('view-proposal.html');
    }
    
    this.retreiveProposal = function() {
        this.id = $sessionStorage.viewAppId;
        console.log("getting pdf proposal with applicationid of ... " + this.id);
        //var base64str = $http.get('/api/proposal', { params: {'applicationId': 3}});
        
        proposalDAO.get({'applicationId': this.id},
        //success
        function(proposal) {
            str = "";
            for (var key in proposal) {
                if (proposal[key] === "[") {
                        break;
                 }
                str += proposal[key];
                this.base64str = str.split("[")[0];
            }
            
            var obj = document.createElement("OBJECT");
            obj.setAttribute("data", "data:application/pdf;base64," + this.base64str);
            obj.setAttribute("width", "1000");
            obj.setAttribute("height", "1000");
            document.body.appendChild(obj);
        },
        //fail
        function() {
            console.log("could not get proposal");
            var error = document.createElement("div");
            error.setAttribute("id", "missingProposal");
            var message = document.createTextNode("Oh no, It looks like the proposal you are looking for has not been submitted.");
            error.appendChild(message);
            
            document.body.appendChild(error);
        });
       
       
    
      
    };
});



module.controller('EvaluatorController',function(evaluatorSignInDAO, $sessionStorage, $window) {
    this.signInMessage ="Please sign in to continue.";
    
    this.signOut = function() {
        delete $sessionStorage.evaluator;
        $window.location.href ='index.html';
     
//        delete $sessionStorage;
        $window.location.href ='index.html';
    };
    
    //alias 'this' so that we can access it inside callback functions
    let ctrl = this;
    this.signIn = function(username, password) {
        //get evaluator from web service
        evaluatorSignInDAO.get({'username': username},
        //success
        function(evaluator) {
            //also store the retrieved evaluator
            $sessionStorage.evaluator = evaluator;
            //redirect to home
            $window.location.href ='.';
        },
        //fail
        function() {
            ctrl.signInMessage ='Sign in failed. Please try again.';
        });
    };
    this.singedIn =
    this.checkSignIn = function () {
        // has the evaluator been added to the session?
        if ($sessionStorage.evaluator) {
            this.username = $sessionStorage.evaluator.username;
            this.department = $sessionStorage.evaluator.department;
            this.email = $sessionStorage.evaluator.email;
            this.signedIn = true;
            this.welcomeEvaluator = "Welcome " + $sessionStorage.evaluator.firstname;
    };
  };
});

module.controller('ApplicantController',function(applicantSignInDAO, $sessionStorage, $window) {
    this.signInMessage ="Please sign in to continue.";
    
    this.signOut = function() {
                delete $sessionStorage.applicant;

        $window.location.href ='index.html';
    };
    
    //alias 'this' so that we can access it inside callback functions
    let ctrl = this;
    this.signIn = function(username, password) {
        //get applicant from web service
         applicantSignInDAO.get({'username': username, 'password': password},
        //success
        function(applicant) {
            //also store the retrieved applicant
            $sessionStorage.applicant = applicant;
            //redirect to home
            $window.location.href ='.';
        },
        //fail
        function() {
            ctrl.signInMessage ='Sign in failed. Please try again.';
        });
    };
    this.singedIn =
    this.checkSignIn = function () {
        // has the applicant been added to the session?
        if ($sessionStorage.applicant) {
            this.signedIn = true;
            this.name = $sessionStorage.applicant.username;
            this.department = $sessionStorage.applicant.department;
            this.telephone = $sessionStorage.applicant.telephone;
            this.email = $sessionStorage.applicant.email;
            this.welcomeApplicant = "Welcome " + $sessionStorage.applicant.firstname;
    };
  };
});


module.filter('unique', function(){
    return function(collection,keyname){
        var output = [],keys = [];
        angular.forEach(collection, function(item){
            var key = item[keyname];
            if(keys.indexOf(key) === -1){
                keys.push(key);
                output.push(item);
            }
        });
        return output;
    };
});

module.controller('GrantController', function (grantDAO , departmentDAO) {

this.grants = grantDAO.query();
this.departments = departmentDAO.query();

//this.selectDepartment = function (selectedDep) {
//    this.grants = departmentDAO.query({"dep": selectedDep});
//};


this.allGrants = function () {
    this.grants = grantDAO.query();  
};
});

module.controller('FormController', function($sessionStorage,$window) {
    this.selectedGrant = $sessionStorage.selectedGrant;
    
    this.apply = function(selectedGrant) {
        $sessionStorage.selectedGrant = selectedGrant;
        $window.location.href = 'application_form.html';
    };
});


module.controller('ApplicationController', function(applicationDAO,evaluationDAO,evaluatorSignInDAO, $scope, applicantSignInDAO,  $sessionStorage, $window, applicationByGrantDAO){
    
    this.applications = applicationDAO.query();
    this.applicationsByGrant = applicationByGrantDAO.query();
    

    
    this.allApplications = function () {
    this.applications = applicationDAO.query();  
    
};

this.retreiveApplications = function() {
        this.myApplications = applicationByGrantDAO.query({"applicantId": $sessionStorage.applicant.staffId});
    };
    
    this.submit = function(application){
        alert("great job!");
        $sessionStorage.application = application;
        this.application = $sessionStorage.application;

        //application.status = "saved";
        //applicationDAO.save(null, application,  
        //function(){
            //$window.location.href = 'confirm-password.html';
        //});
        $window.location.href = 'confirm-submission.html';
      };
    
    this.application = $sessionStorage.application;

    this.signIn = function(username, password) {
        //get applicant from web service
        applicantSignInDAO.get({'username': username, 'password': password},
        //success
        function(applicant) {
            //also store the retrieved applicant
            $sessionStorage.applicant = applicant;
            application.applicant = $sessionStorage.applicant;
            //redirect to home
            $window.location.href ='.';
        },
        //fail
        function() {
            ctrl.signInMessage ='Sign in failed. Please try again.';
        });
    };
    

    this.save = function(password){
        username = $sessionStorage.applicant.username;
        applicantSignInDAO.get({'username': username, 'password': password},
        //success
        function(applicant) {
            this.application = $sessionStorage.application;
            this.application.grant = $sessionStorage.selectedGrant;
            this.application.status = "saved";
            this.application.applicant = $sessionStorage.applicant;
            applicationDAO.save(null, this.application, 
            
            function(){
                $window.location.href = 'submitted-message.html';
            });      
        },
        //fail
        function() {
            ctrl.signInMessage ='Sign in failed. Please try again.';
        });
        
    };
    
    
      $scope.editorEnabled = false;
    
     $scope.confirm = function() {
       $scope.submitted = true;
       $scope.editorEnabled = true;
  };
  
  
  $scope.edit = function() {
    $scope.editorEnabled = false;
    //$scope.submitted = false;
    
  };  
    
  $scope.goBack =function(application){  
      $window.history.back(); 
  };
  
      this.view = function(selectedGrant){
        $sessionStorage.selectedGrant = selectedGrant;
        $window.location.href = 'view-applications.html';
    };
    
    this.loadApps = function(selectedGrant) {
        console.log("dssdedfedfefde");
       this.allApplications = [];
        grantId = $sessionStorage.selectedGrant.grantId;
        console.log(grantId);
        this.allApplications = applicationByGrantDAO.query({"grantId":grantId});
        $sessionStorage.applications = this.allApplications;
    };
    
    
    this.evaluate = function(selectedApplication){
        $sessionStorage.selectedApplication = selectedApplication;  
        // select applicationid before upload of proposal
        $sessionStorage.viewAppId = selectedApplication.applicationId;
        $window.location.href = 'review-Application.html'; 
    };
    
    
    this.selectedApplication = $sessionStorage.selectedApplication;
    
    $scope.status = ["Accepted", "Declined", "Need More information"]; 
    
    
    this.signIn = function(username, password) {
        //get applicant from web service
        evaluatorSignInDAO.get({'username': username, 'password': password},
        //success
        function(evaluator) {
            //also store the retrieved applicant
            $sessionStorage.evaluator = evaluator;
            application.evaluator = $sessionStorage.evaluator;
         
        },
        //fail
        function() {
            ctrl.signInMessage ='Sign in failed. Please try again.';
        });
    };
    
    
    this.submitEvaluation = function(evaluation){
        $sessionStorage.evaluation = evaluation;           
        this.evaluation = $sessionStorage.evaluation;
        this.evaluation.evaluator = $sessionStorage.evaluator;
        this.evaluation.application = $sessionStorage.selectedApplication;
        this.evaluation.application.status = this.evaluation.status;
        
        delete this.evaluation.status;

       evaluationDAO.save(null, this.evaluation,  
            function(){
//                                delete $sessionStorage.evaluation;

                $window.location.href = 'index.html';
           });      
        
         };
    
    
    
    
    });
