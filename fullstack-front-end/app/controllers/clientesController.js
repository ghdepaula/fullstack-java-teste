(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('clientesController', clientesController);

	clientesController.$inject = ['clienteService','anexoService', 'regimesTributariosService', 'alertsService', 'cfpLoadingBar', '$scope', '$filter', '$timeout', '$window'];

	function clientesController(clienteService, anexoService, regimesTributariosService, alertsService, cfpLoadingBar, $scope, $filter, $timeout, $window) {
		
		$scope.clientes = [];
		$scope.cliente;
		$scope.anexos = [];
		$scope.regimesTributarios = [];
		$scope.anexosChecked = [];
		$scope.hideAdd = false;
		$scope.showAnexos = false;
		$scope.showAlert = false;
		$scope.alertMessage;

		$scope.findAll = function () {
			findAll();
		}
		
		$scope.findAnexos = function(){
			findAnexos();
		}
		
		$scope.findRegimesTributarios = function(){
			findRegimesTributarios();
		}

		$scope.loadForm = function (idCliente) {
			
			cfpLoadingBar.start();

			
			clienteService.findById(idCliente).success(function (data) {
				
				$scope.cliente = data;
				$scope.anexosChecked = data.anexos;
				$scope.hideAdd = true;
				$scope.showAnexos = data.anexos.length > 0;
				
				$('#cnpjCliente').attr('readonly', 'cnpjCliente');
			});
			
			cfpLoadingBar.complete();

		}
		
		$scope.onChangeRegime = function(codRegimeTributario){
			
			cfpLoadingBar.start();
			
			regimesTributariosService.findById(codRegimeTributario).success(function(data){
				
				if(data.enabledAnexos){
					$scope.showAnexos = true;
				}else{
					$scope.showAnexos = false;
					$scope.anexosChecked = [];
				}
			});
			
			cfpLoadingBar.start();
		
		}

		$scope.insert = function (cliente) {
			
			cfpLoadingBar.start();
			
			var msg;
			cliente.anexos = $scope.anexosChecked;
			
			clienteService.insert(cliente).success(function (data) {
				
				msg = alertsService.alertSuccess("Cliente cadastrado com sucesso !");
				showAlert(msg);
				clearData();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível cadastrar o cliente", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}

		$scope.update = function (cliente) {
			
			cfpLoadingBar.start();
			
			var msg;
			cliente.anexos = $scope.anexosChecked;
			
			clienteService.update(cliente).success(function (data) {
				
				msg = alertsService.alertSuccess("Cliente atualizado com sucesso !");
				showAlert(msg);
				clearData();;
			
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível atualizar o cliente", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
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
				(result);
				$scope.clientes = result;
			});
		}
		
		function findAnexos(){
			anexoService.findAll().success(function(result){
				$scope.anexos = result;
			});
		}
		
		function findRegimesTributarios(){
			regimesTributariosService.findAll().success(function(result){
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
			findAnexos();
			findRegimesTributarios();
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
		
		function showAlert(alertMessage) {
			$scope.alertMessage = alertMessage;
			$scope.showAlert = true;
			$window.scrollTo(0, 0);
			
			$timeout(function(){
				$scope.showAlert = false;
				$scope.alertMessage = null;
			}, 5000);
			
		}
		
	    $scope.start = function() {
	       cfpLoadingBar.start();
	    };

	    $scope.complete = function () {
	       cfpLoadingBar.complete();
	    };


	      // fake the initial load so first time users can see the bar right away:
	    $scope.start();
	    $scope.loadIntro = true;
	    $timeout(function() {
		    $scope.complete();
		    $scope.loadIntro = false;
	    }, 1250);
		
	}

})();