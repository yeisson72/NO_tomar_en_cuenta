function DetalleVehiculo(row) {
    d3.json("http://localhost:8089/rest/vehiculos/"+row.placa, function(vehiculo) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",vehiculo.placa)
        .attr("id","placa").
        attr("type","hidden");
      div.append("h2").text("Detalle de vehiculo");
      var table = div.append("table");
      var tr = table.append("tr");
      tr.append("td").text("Placa:");
      tr.append("td").append("input")
        .attr("value",vehiculo.placa)
        .attr("id","placa");
      tr.append("td").text("Ruta:");
      tr.append("td").append("input")
        .attr("value",vehiculo.ruta)
        .attr("id","ruta");
      div.append("input").attr("type","button")
         .attr("value","Actualizar").attr("id","button")
         .attr("onClick","ActualizarVehiculo(this)");
      div.append("input").attr("type","button")
         .attr("value","Eliminar").attr("id","button")
         .attr("onClick","EliminarVehiculo(this)");
    });
  }