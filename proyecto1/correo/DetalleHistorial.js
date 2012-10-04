function DetalleHistorial(row) {
    d3.json("http://localhost:8089/rest/historiales/"+row.codigo, function(historial) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",historial.codigo)
        .attr("id","codigo").
        attr("type","hidden");
      div.append("h2").text("Detalle de historial");
      var table = div.append("table");
      var tr = table.append("tr");
      tr.append("td").text("Codigo:");
      tr.append("td").append("input")
        .attr("value",historial.codigo)
        .attr("id","codigo");
	  d3.json("http://localhost:8089/rest/personas", function(json) {
		var slt = table.selectAll("tr")
                  .data(json.persona)
                  .enter().append("tr");
		  slt.append("td").append("select").attr("id","persona"););
		  slt.append("option").attr("value",function(d) {return d.cedula;})
				  .text(function(d) {return d.zona;});
	  });
      tr = table.append("tr");
      tr.append("td").text("Fecha:");
      tr.append("td").append("input")
        .attr("value",historial.fecha)
        .attr("id","fecha");
      tr.append("td").text("descripcion:");
      tr.append("td").append("input")
        .attr("value",historial.descripcion)
        .attr("id","descripcion");
      d3.json("http://localhost:8089/rest/paquetes", function(json) {
		var slt = table.selectAll("tr")
                  .data(json.paquete)
                  .enter().append("tr");
		  slt.append("td").append("select").attr("id","paquete"););
		  slt.append("option").attr("value",function(d) {return d.codigo;})
				  .text(function(d) {return d.remitente;});
	  });
      tr.append("td").append("input")
        .attr("value",historial.razon_social)
        .attr("id","razon_social");
      div.append("input").attr("type","button")
         .attr("value","Actualizar").attr("id","button")
         .attr("onClick","ActualizarHistorial(this)");
      div.append("input").attr("type","button")
         .attr("value","Eliminar").attr("id","button")
         .attr("onClick","EliminarHistorial(this)");
    });
  }