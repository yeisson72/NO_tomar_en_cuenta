function ListadoPersona() {
    d3.json("http://localhost:8089/rest/personas", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de Persaonas");
      var table = div.append("table");
      var thead = table.append("thead");
	  thead.append("th").text("Cedula");
      thead.append("th").text("Nombre");
	  thead.append("th").text("Telefono");
      thead.append("th").text("Opcervacion");
      thead.append("th").text("Tipo");
      var tr = table.selectAll("tr")
                  .data(json.persona)
                  .enter().append("tr")
                  .attr("onClick","DetallePersona(this)");
      tr.attr("cedula",function(d) {return d.cedula;});
      tr.append("td").text(function(d) {return d.nombre;});
      tr.append("td").text(function(d) {return d.telefono;});
      tr.append("td").text(function(d) {return d.opcervacion});
      tr.append("td").text(function(d) {return d.tipo;});
      div.append("input").attr("type","button")
         .attr("value","Agregar").attr("id","button")
         .attr("onClick","AgregarPersona(this)");
    });
  }