function ListadoHistoriales() {
    d3.json("http://localhost:8089/rest/historiales", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de Historial");
      var table = div.append("table");
      var thead = table.append("thead");
	  thead.append("th").text("Codigo");
      thead.append("th").text("Persona");
	  thead.append("th").text("Fecha");
      thead.append("th").text("Descripcion");
      thead.append("th").text("Paqueta");
      var tr = table.selectAll("tr")
                  .data(json.resinto)
                  .enter().append("tr")
                  .attr("onClick","DetalleResinto(this)");
      tr.attr("codigo",function(d) {return d.codigo;});
	  tr.append("td").text(function(d) {return d.persona;});
      tr.append("td").text(function(d) {return d.fecha;});
      tr.append("td").text(function(d) {return d.descripcion;});
      tr.append("td").text(function(d) {return d.paquete;});
      div.append("input").attr("type","button")
         .attr("value","Agregar").attr("id","button")
         .attr("onClick","AgregarHistorial(this)");
    });
  }