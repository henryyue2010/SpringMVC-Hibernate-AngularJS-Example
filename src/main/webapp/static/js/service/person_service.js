'use strict';

angular.module('myApp').factory('PersonService', ['$http', '$q','$location', function($http, $q, $location){

    var REST_SERVICE_URI = $location.$$absUrl + '/person/'
    
    var factory = {
        fetchAllPersons: fetchAllPersons,
        createPerson: createPerson,
        updatePerson:updatePerson,
        deletePerson:deletePerson
    };

    return factory;

    function fetchAllPersons() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Persons');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createPerson(person) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, person)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Person');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updatePerson(person, pid) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+pid, person)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Person');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deletePerson(pid) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+pid)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Person');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
