function DetallePaquete(row) {
    d3.json("http://localhost:8089/rest/paquetes/"+row.codigo, function(paquete) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",paquete.codigo)
        .attr("id","codigo").
        attr("type","hidden");
      div.append("h2").text("Detalle de paquete");
      var table = div.append("table");
      var tr = table.append("tr");
      tr.append("td").text("Codigo:");
      tr.append("td").append("input")
        .attr("value",paquete.codigo)
        .attr("id","codigo");
      tr.append("td").text("Remitente:");
      tr.append("td").append("input")
        .attr("value",paquete.remitente)
        .attr("id","remitente");
      tr = table.append("tr");
      tr.append("td").text("Destinatario:");
      tr.append("td").append("input")
        .attr("value",paquete.destinatario)
        .attr("id","destinatario");
		
	  d3.json("http://localhost:8089/rest/resintos", function(json) {
		var slt = table.selectAll("tr")
                  .data(json.resinto)
                  .enter().append("tr");
		  slt.append("td").append("select").attr("id","resinto"););
		  slt.append("option").attr("value",function(d) {return d.codigo;})
				  .text(function(d) {return d.zona;});
	  });
	  
      tr.append("td").text("Peso:");
      tr.append("td").append("input")
        .attr("value",paquete.peso)
        .attr("id","peso");		
      tr = table.append("tr");
      tr.append("td").text("Cobro:");
      tr.append("td").append("input")
        .attr("value",paquete.cobro)
        .attr("id","cobro");
      tr.append("td").text("Monto:");
      tr.append("td").append("input")
        .attr("value",paquete.monto)
        .attr("id","monto");
      div.append("input").attr("type","button")
         .attr("value","Actualizar").attr("id","button")
         .attr("onClick","ActualizarPaquete(this)");
      div.append("input").attr("type","button")
         .attr("value","Eliminar").attr("id","button")
         .attr("onClick","EliminarPaquete(this)");
    });
  }