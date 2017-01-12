(function () {
	'use strict';

	angular.module('nf_app').factory('anexoService', anexoService);

	anexoService.$inject = ['$http'];

	function anexoService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/anexos');
		}
		
		var _insert = function (anexo) {
			return $http.post('http://localhost:8081/contabilizei/rest/anexos', anexo);	
		}

		
		var _update = function (anexo) {
			return $http.put('http://localhost:8081/contabilizei/rest/anexos', anexo);	
		}

		var _findById = function (codAnexo) {
			return $http.get('http://localhost:8081/contabilizei/rest/anexos/' + codAnexo);	
		}
		
		var _findByStatus = function (statusAnexo) {
			return $http.get('http://localhost:8081/contabilizei/rest/anexos/actives');	
		}

		return {
			findAll : _findAll,
			findById : _findById,
			findByStatus : _findByStatus,
			insert : _insert,
			update : _update
		}
	}

})();