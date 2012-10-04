function ListadoResintos() {
    d3.json("http://localhost:8089/rest/resintos", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de Resinto");
      var table = div.append("table");
      var thead = table.append("thead");
	  thead.append("th").text("Codigo");
      thead.append("th").text("Zona");
	  thead.append("th").text("Calle");
      thead.append("th").text("Avenida");
      thead.append("th").text("Distancia");
      thead.append("th").text("Latitud");
	  thead.append("th").text("Longitud");
	  thead.append("th").text("Razon Social");
      var tr = table.selectAll("tr")
                  .data(json.resinto)
                  .enter().append("tr")
                  .attr("onClick","DetalleResinto(this)");
      tr.attr("codigo",function(d) {return d.codigo;});
      tr.append("td").text(function(d) {return d.zona;});
      tr.append("td").text(function(d) {return d.calle;});
      tr.append("td").text(function(d) {return d.avenida;});
      tr.append("td").text(function(d) {return d.distancia;});
	  tr.append("td").text(function(d) {return d.latitud;});
	  tr.append("td").text(function(d) {return d.longiud;});
	  tr.append("td").text(function(d) {return d.razon_social;});
      div.append("input").attr("type","button")
         .attr("value","Agregar").attr("id","button")
         .attr("onClick","AgregarResinto(this)");
    });
  }