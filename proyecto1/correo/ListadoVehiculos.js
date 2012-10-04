function ListadoVehiculos() {
    d3.json("http://localhost:8089/rest/vehiculos", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de Vehiculo");
      var table = div.append("table");
      var thead = table.append("thead");
	  thead.append("th").text("Placa");
      thead.append("th").text("Ruta");
      var tr = table.selectAll("tr")
                  .data(json.resinto)
                  .enter().append("tr")
                  .attr("onClick","DetalleVehiculo(this)");
      tr.attr("placa",function(d) {return d.placa;});
      tr.append("td").text(function(d) {return d.placa;});
      tr.append("td").text(function(d) {return d.ruta;});
         .attr("value","Agregar").attr("id","button")
         .attr("onClick","AgregarVehiculo(this)");
    });
  }