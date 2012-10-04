function DetalleOficina(row) {
    d3.json("http://localhost:8089/rest/oficinas/"+row.codigo, function(oficina) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",oficina.codigo)
        .attr("id","codigo").
        attr("type","hidden");
      div.append("h2").text("Detalle de oficina");
      var table = div.append("table");
      var tr = table.append("tr");
      tr.append("td").text("Codigo:");
      tr.append("td").append("input")
        .attr("value",oficina.codigo)
        .attr("id","codigo");
      tr.append("td").text("Telefono:");
      tr.append("td").append("input")
        .attr("value",oficina.telefono)
        .attr("id","telefono");
	  d3.json("http://localhost:8089/rest/resintos", function(json) {
		var slt = table.selectAll("tr")
                  .data(json.resinto)
                  .enter().append("tr");
		  slt.append("td").append("select").attr("id","resinto"););
		  slt.append("option").attr("value",function(d) {return d.codigo;})
				  .text(function(d) {return d.zona;});
	  });
      d3.json("http://localhost:8089/rest/personas", function(json) {
		var slt = table.selectAll("tr")
                  .data(json.persona)
                  .enter().append("tr");
		  slt.append("td").append("select").attr("id","persona"););
		  slt.append("option").attr("value",function(d) {return d.cedula;})
				  .text(function(d) {return d.zona;});
	  });
      div.append("input").attr("type","button")
         .attr("value","Actualizar").attr("id","button")
         .attr("onClick","ActualizarOficina(this)");
      div.append("input").attr("type","button")
         .attr("value","Eliminar").attr("id","button")
         .attr("onClick","EliminarOficina(this)");
    });
  }
  