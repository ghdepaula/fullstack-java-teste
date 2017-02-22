(function () {
	'use strict';

	angular.module('nf_app').controller('clientesController', clientesController);

	clientesController.$inject = ['clienteService','anexoService', 'regimeTributarioService', '$scope', '$filter'];

	function clientesController(clienteService, anexoService, regimeTributarioService, $scope, $filter) {
		
		$scope.clientes = [];
		$scope.cliente;
		$scope.anexos = [];
		$scope.regimesTributarios = [];
		$scope.anexosChecked = [];
		$scope.hideAdd = false;
		$scope.hideAnexos = false;

		$scope.findAll = function () {
			findAll();
		}
		
		$scope.findAnexosActives = function(){
			findAnexosActives();
		}
		
		$scope.findRegsTributariosActives = function(){
			findRegsTributariosActives();
		}

		$scope.loadForm = function (idCliente) {
			clienteService.findById(idCliente).success(function (data) {
				$scope.cliente = data;
				$scope.anexosChecked = data.anexos;
				$scope.hideAdd = true;
				
				console.log(data);
				
				$('#cnpjCliente').attr('readonly', 'cnpjCliente');
			});
		}
		
		$scope.onChangeRegime = function(){
			if($scope.cliente.codRegimeTributario != 5){
				$scope.hideAnexos = false;
			}else{
				$scope.anexosChecked = [];
				$scope.hideAnexos = true;
			}
		}

		$scope.insert = function (cliente) {
			
			cliente.anexos = $scope.anexosChecked;
			
			clienteService.insert(cliente).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.update = function (cliente) {
			
			cliente.anexos = $scope.anexosChecked;
			
			clienteService.update(cliente).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.remove = function (idCliente) {
			clienteService.remove(idCliente).success(function (data) {				
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}
		
		$scope.testeHide = function(){
			return true;
		}
		

		$scope.checkAnexo = function(anexo) {
		    var arrayAnexosChecked = $scope.anexosChecked;
		    return (arrayAnexosChecked.indexOfObject(arrayAnexosChecked, anexo.codAnexo, "codAnexo") > - 1);
		}
		  
		$scope.toggleChecked = function(anexo) {
			
		    var index = $scope.anexosChecked.indexOfObject($scope.anexosChecked, anexo.codAnexo, "codAnexo");
		    
		    if (index > -1) {
		    	$scope.anexosChecked.splice(index, 1);
		    } else {
		    	$scope.anexosChecked.push(anexo);
		    }
		}

		function findAll() {
			clienteService.findAll().success(function(result){
				$scope.clientes = result;
			});
		}
		
		function findAnexosActives(){
			anexoService.findByStatusActive().success(function(result){
				$scope.anexos = result;
			});
		}
		
		function findRegsTributariosActives(){
			regimeTributarioService.findByStatusActive().success(function(result){
				$scope.regimesTributarios = result;
			});
		}
		
		function clearData() {
			$scope.cliente = null;
			$scope.anexos = [];
			$scope.anexosChecked = [];
			$scope.regimesTributarios = [];
			$scope.hideAdd = false;

			resetCliente();
			findAll();
			findAnexosActives();
			findRegsTributariosActives();
		}
		
		$scope.cancel = function () {
			clearData();
		}

		function resetCliente() {
			$('#nomeRazaoSocial').val('');
			$('#regimeTributario').val('')
			$('#cnpjCliente').val('');
			$('#telefone').val('');
			$('#email').val('');
			$('#cnpjCliente').removeAttr('readonly', 'cnpjCliente');
		}

		function showError(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}
	}

})();