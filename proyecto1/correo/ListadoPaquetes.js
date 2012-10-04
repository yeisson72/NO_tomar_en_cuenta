function ListadoPaquetes() {
    d3.json("http://localhost:8089/rest/paquetes", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de Paquetes");
      var table = div.append("table");
      var thead = table.append("thead");
	  thead.append("th").text("Codigo");
      thead.append("th").text("Remitente");
	  thead.append("th").text("Destinatario");
      thead.append("th").text("Resinto");
      thead.append("th").text("Peso");
      thead.append("th").text("Cobro");
	  thead.append("th").text("Monto");
      var tr = table.selectAll("tr")
                  .data(json.resinto)
                  .enter().append("tr")
                  .attr("onClick","DetallePaquete(this)");
      tr.attr("codigo",function(d) {return d.codigo;});
      tr.append("td").text(function(d) {return d.remitente;});
      tr.append("td").text(function(d) {return d.destinatario;});
      tr.append("td").text(function(d) {return d.resinto;});
      tr.append("td").text(function(d) {return d.peso;});
	  tr.append("td").text(function(d) {return d.cobro;});
	  tr.append("td").text(function(d) {return d.monto;});
      div.append("input").attr("type","button")
         .attr("value","Agregar").attr("id","button")
         .attr("onClick","AgregarPaquete(this)");
    });
  }