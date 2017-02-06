(function () {
	'use strict';

	angular.module('nf_app').factory('regimeTributarioService', regimeTributarioService);

	regimeTributarioService.$inject = ['$http'];

	function regimeTributarioService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/regtributarios');
		}
		
		var _insert = function (anexo) {
			return $http.post('http://localhost:8081/contabilizei/rest/regtributarios', anexo);	
		}

		
		var _update = function (anexo) {
			return $http.put('http://localhost:8081/contabilizei/rest/regtributarios', anexo);	
		}

		var _findById = function (codAnexo) {
			return $http.get('http://localhost:8081/contabilizei/rest/regtributarios/' + codAnexo);	
		}
		
		var _findByStatusActive = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/regtributarios/actives');	
		}

		return {
			findAll : _findAll,
			findById : _findById,
			findByStatusActive : _findByStatusActive,
			insert : _insert,
			update : _update
		}
	}

})();