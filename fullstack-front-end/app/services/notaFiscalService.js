(function () {
	'use strict';

	angular.module('nf_app').factory('notaFiscalService', notaFiscalService);

	notaFiscalService.$inject = ['$http'];

	function notaFiscalService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/notas');
		}
		
		var _insert = function (nota) {
			return $http.post('http://localhost:8081/contabilizei/rest/notas', nota);	
		}

		
		var _update = function (nota) {
			return $http.put('http://localhost:8081/contabilizei/rest/notas', nota);	
		}

		var _remove = function (numNotaFiscal) {
			return $http.delete('http://localhost:8081/contabilizei/rest/notas/' + numNotaFiscal);	
		}

		var _findById = function (numNotaFiscal) {
			return $http.get('http://localhost:8081/contabilizei/rest/notas/' + numNotaFiscal);	
		}
		
		var _findByCodCliente = function (codCliente) {
			return $http.get('http://localhost:8081/contabilizei/rest/notas/cliente/' + codCliente);	
		}

		return {
			insert : _insert,
			update : _update,
			remove : _remove,
			findAll : _findAll,
			findById : _findById,
			findByCodCliente :_findByCodCliente
		}
	}

})();