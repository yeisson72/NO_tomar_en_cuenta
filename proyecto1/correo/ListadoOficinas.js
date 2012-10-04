function ListadoOficinas() {
    d3.json("http://localhost:8089/rest/oficinas", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de Oficinas");
      var table = div.append("table");
      var thead = table.append("thead");
	  thead.append("th").text("Codigo");
      thead.append("th").text("Telefono");
	  thead.append("th").text("Resinto");
      thead.append("th").text("Persona");
      var tr = table.selectAll("tr")
                  .data(json.resinto)
                  .enter().append("tr")
                  .attr("onClick","DetalleOficina(this)");
      tr.attr("codigo",function(d) {return d.codigo;});
      tr.append("td").text(function(d) {return d.telefono;});
      tr.append("td").text(function(d) {return d.resinto;});
      tr.append("td").text(function(d) {return d.persona;});
      div.append("input").attr("type","button")
         .attr("value","Agregar").attr("id","button")
         .attr("onClick","AgregarOficina(this)");
    });
  }