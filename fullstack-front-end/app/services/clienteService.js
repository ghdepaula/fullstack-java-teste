(function () {
	'use strict';

	angular.module('contabilizeiApp').factory('clienteService', clienteService);

	clienteService.$inject = ['$http'];

	function clienteService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/clientes');
		}
		
		var _insert = function (cliente) {
			return $http.post('http://localhost:8081/contabilizei/rest/clientes', cliente);	
		}
		
		var _update = function (cliente) {
			return $http.put('http://localhost:8081/contabilizei/rest/clientes', cliente);	
		}

		var _findById = function (idCliente) {
			return $http.get('http://localhost:8081/contabilizei/rest/clientes/' + idCliente);	
		}

		return {
			findAll : _findAll,
			findById : _findById,
			insert : _insert,
			update : _update,
			remove : _remove
		}
	}

})();