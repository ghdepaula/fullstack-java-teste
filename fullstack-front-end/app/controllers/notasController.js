(function () {
	'use strict';

	angular.module('nf_app').controller('notasController', notasController);

	notasController.$inject = ['clienteService','anexoService', 'notaFiscalService', '$scope', '$filter'];

	function notasController(clienteService, anexoService, notaFiscalService, $scope, $filter) {
		
		//Var initialize
		$scope.notas = [];
		$scope.nota;
		$scope.codCliente;
		$scope.clientes = [];
		$scope.anexos = [];
		$scope.hideAdd = false;
		$scope.clienteDisabled = true;

		
		//Data Load
		$scope.findByCodCliente = function (codCliente) {
			findByCodCliente(codCliente);
		}
		
		$scope.findClientes = function(){
			findClientes();
		}
		
		$scope.findAnexos = function(){
			findAnexos();
		}
		
		function findByCodCliente(codCliente) {
			notaFiscalService.findByCodCliente(codCliente).success(function(result){
				$scope.notas = result;
			});
		}
		
		function findClientes() {
			clienteService.findAll().success(function(result){
				$scope.clientes = result;
			});
		}
		
		function findAnexos(){
			anexoService.findByStatusActive().success(function(result){
				$scope.anexos = result;
			});
		}
		
		//Form Events
		$scope.loadForm = function (numNotaFiscal) {
			notaFiscalService.findById(numNotaFiscal).success(function (data) {
				$scope.nota = data;
				$scope.hideAdd = true;
				
				if(data.clienteDTO.anexos.length > 0){
					$scope.anexos = data.clienteDTO.anexos; 
				}else{
					findAnexos();
				}
			});
		}
		
		$scope.onSelectCliente = function(codCliente){
			if(codCliente){
				
				findByCodCliente(codCliente);
				
				clienteService.findById(codCliente).success(function (data) {
					if(data.anexos.length > 0){
						$scope.anexos = data.anexos;
					}else{
						findAnexos();
					}
				});
				
			}
		}
		
		$scope.validateNumNota = function(numNotaFiscal){
			if(numNotaFiscal){
				notaFiscalService.findById(numNotaFiscal).success(function (data) {
					if(data.numNotaFiscal){
						$scope.notaFiscalForm.numNotaFiscal.$setValidity("numNotaFiscal", false);
					}else{
						$scope.notaFiscalForm.numNotaFiscal.$setValidity("numNotaFiscal", true);
					}
				});
			}
		}
		
		function clearData() {
			var codCliente = $scope.nota.codCliente;
			resetNotaFiscal();
			findByCodCliente(codCliente);
			$scope.hideAdd = false;
		}
		
		$scope.cancel = function () {
			clearData();
		}

		function resetNotaFiscal() {
			
			$scope.nota.numNotaFiscal = null;
			$scope.nota.descricaoNotaFiscal = null;
			$scope.nota.codAnexo = null;
			$scope.nota.valorNotaFiscal = null;
			$scope.nota.dataEmissao = null;
			
			$('#numNotaFiscal').val('');
			$('#dtEmissaoNota').val('')
			$('#anexoNotaFiscal').val('');
			$('#descrNotaFiscal').val('');
			$('#vlrNotaFiscal').val('');
		}

		function showError(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}

		//Form operations
		$scope.insert = function (nota) {
			notaFiscalService.insert(nota).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.update = function (nota) {
			notaFiscalService.update(nota).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.remove = function (numNotaFiscal) {
			notaFiscalService.remove(numNotaFiscal).success(function (data) {				
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}
	}

})();