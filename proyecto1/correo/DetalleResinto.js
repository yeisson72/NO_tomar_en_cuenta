function DetalleResinto(row) {
    d3.json("http://localhost:8089/rest/resintos/"+row.codigo, function(resinto) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",resinto.codigo)
        .attr("id","codigo").
        attr("type","hidden");
      div.append("h2").text("Detalle de resinto");
      var table = div.append("table");
      var tr = table.append("tr");
      tr.append("td").text("Codigo:");
      tr.append("td").append("input")
        .attr("value",resinto.codigo)
        .attr("id","codigo");
      tr.append("td").text("Zona:");
      tr.append("td").append("input")
        .attr("value",resinto.zona)
        .attr("id","zona");
      tr = table.append("tr");
      tr.append("td").text("calle:");
      tr.append("td").append("input")
        .attr("value",resinto.calle)
        .attr("id","calle");
      tr.append("td").text("Avenida:");
      tr.append("td").append("input")
        .attr("value",resinto.avenida)
        .attr("id","avenida");
      tr = table.append("tr");
      tr.append("td").text("Distancia:");
      tr.append("td").append("input")
        .attr("value",resinto.distancia)
        .attr("id","distancia");
      tr.append("td").text("Latitud:");
      tr.append("td").append("input")
        .attr("value",resinto.latitud)
        .attr("id","latitud");
	  tr = table.append("tr");
      tr.append("td").text("Longitud:");
      tr.append("td").append("input")
        .attr("value",resinto.longitud)
        .attr("id","longitud");;
      tr.append("td").text("Razon Social:");
      tr.append("td").append("input")
        .attr("value",resinto.razon_social)
        .attr("id","razon_social");
      div.append("input").attr("type","button")
         .attr("value","Actualizar").attr("id","button")
         .attr("onClick","ActualizarResinto(this)");
      div.append("input").attr("type","button")
         .attr("value","Eliminar").attr("id","button")
         .attr("onClick","EliminarResinto(this)");
    });
  }