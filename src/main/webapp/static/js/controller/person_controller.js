'use strict';

angular.module('myApp').controller('PersonController', ['$scope', 'PersonService', function($scope, PersonService) {
    var self = this;
    self.person={pid:null, name:'', location:''};
    self.persons=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllPersons();

    function fetchAllPersons(){
        PersonService.fetchAllPersons()
            .then(
            function(d) {
                self.persons = d;
            },
            function(errResponse){
                console.error('Error while fetching Persons');
            }
        );
    }

    function createPerson(person){
        PersonService.createPerson(person)
            .then(
            fetchAllPersons,
            function(errResponse){
                console.error('Error while creating Person');
            }
        );
    }

    function updatePerson(person, pid){
        PersonService.updatePerson(person, pid)
            .then(
            fetchAllPersons,
            function(errResponse){
                console.error('Error while updating Person');
            }
        );
    }

    function deletePerson(pid){
        PersonService.deletePerson(pid)
            .then(
            fetchAllPersons,
            function(errResponse){
                console.error('Error while deleting Person');
            }
        );
    }

    function submit() {
        if(self.person.pid===null){
            console.log('Saving New Person', self.person);
            createPerson(self.person);
        }else{
            updatePerson(self.person, self.person.pid);
            console.log('Person updated with pid ', self.person.pid);
        }
        reset();
    }

    function edit(pid){
        console.log('pid person to be edited', pid);
        for(var i = 0; i < self.persons.length; i++){
            if(self.persons[i].pid === pid) {
                self.person = angular.copy(self.persons[i]);
                break;
            }
        }
    }

    function remove(pid){
        console.log('pid to be deleted', pid);
        if(self.person.pid === pid) {
            reset();
        }
        deletePerson(pid);
    }


    function reset(){
        self.person={pid:null,name:'',location:''};
        $scope.myForm.$setPristine();
    }

}]);
