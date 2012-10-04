function DetallePersona(row) {
    d3.json("http://localhost:8089/rest/personas/"+row.cedula, function(persona) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",persona.cedula)
        .attr("id","cedula").
        attr("type","hidden");
      div.append("h2").text("Detalle de persona");
      var table = div.append("table");
      var tr = table.append("tr");
      tr.append("td").text("Cedula:");
      tr.append("td").append("input")
        .attr("value",persona.cedula)
        .attr("id","cedula");
      tr.append("td").text("Nombre:");
      tr.append("td").append("input")
        .attr("value",persona.nombre)
        .attr("id","nombre");
      tr = table.append("tr");
      tr.append("td").text("Telefono:");
      tr.append("td").append("input")
        .attr("value",persona.telefono)
        .attr("id","telefono");
      tr.append("td").text("Opcervacion:");
      tr.append("td").append("input")
        .attr("value",persona.opcervacion)
        .attr("id","opcervacion");
      tr = table.append("tr");
      tr.append("td").text("Tipo:");
      tr.append("td").append("input")
        .attr("value",persona.tipo)
        .attr("id","tipo");
      div.append("input").attr("type","button")
         .attr("value","Actualizar").attr("id","button")
         .attr("onClick","ActualizarPersona(this)");
      div.append("input").attr("type","button")
         .attr("value","Eliminar").attr("id","button")
         .attr("onClick","EliminarPersona(this)");
    });
  }