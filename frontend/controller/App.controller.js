sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel"
], function (Controller, JSONModel) {
   "use strict";
   return Controller.extend("sap.io.demo.controller.App", {
      onInit : function () {
		 let serviceUrl = 'http://localhost:8080/api/products'; // update the url in function of your landscape.
		 fetch(serviceUrl).then(function(response) {
			if (response.ok) {
				return response.json();
			} else {
				console.log('Error loading products. Status Code: ' + response.status);
				return {};
			}
			})
			.then(function(data) {
				console.table(data);
				// set data model on view
				var catalog = {
				   products : data
				};
				var oModel = new JSONModel(catalog);
				this.getView().setModel(oModel);
			}.bind(this)
		 );
         
      }
   });
});