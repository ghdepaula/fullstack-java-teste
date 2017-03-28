(function () {
	'use strict';

	angular.module('contabilizeiApp').factory('tributosService', tributosService);

	tributosService.$inject = ['$http'];

	function tributosService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/tributos');
		}
		
		var _insert = function (tributo) {
			return $http.post('http://localhost:8081/contabilizei/rest/tributos', tributo);	
		}

		
		var _update = function (tributo) {
			return $http.put('http://localhost:8081/contabilizei/rest/tributos', tributo);	
		}

		var _findById = function (codTributo) {
			return $http.get('http://localhost:8081/contabilizei/rest/tributos/' + codTributo);	
		}

		return {
			findAll : _findAll,
			findById : _findById,
			insert : _insert,
			update : _update
		}
	}

})();