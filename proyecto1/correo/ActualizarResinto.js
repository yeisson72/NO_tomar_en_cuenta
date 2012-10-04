 d3.xhr2 = function(url, method, params, mime, callback) {
    var req = new XMLHttpRequest;
    if (arguments.length < 5) callback = mime, mime = null;
    else if (mime && req.overrideMimeType) req.overrideMimeType(mime);
    req.open(method, url, true);
    if (mime) req.setRequestHeader("Accept", mime);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.setRequestHeader("Content-length", params.length);
    req.setRequestHeader("Connection", "close");
    req.onreadystatechange = function() {
      if (req.readyState === 4) {
        var s = req.status;
        callback(s >= 200 && s < 300 || s === 304 ? req : null);
      }
    };
    req.send(params);
  };

  function ActualizarResinto() {
    var codigo = d3.select("#codigo").property("value");
    var zona = d3.select("#zona").property("value");
    var calle = d3.select("#calle").property("value");
    var avenida = d3.select("#avenida").property("value");
    var distancia = d3.select("#distancia").property("value");
    var latitud = d3.select("#latitud").property("value");
    var longitud = d3.select("#longitud").property("value");
	var razon_social = d3.select("#razon_social").property("value");
    var params = "codigo="+codigo+"&zona="+zona+"&calle="+calle+
                 "&avenida="+avenida+"&distancia="+distancia+"&longitud="+longitud+
                 "&latitud="+latitud+"&razon_social="+razon_social;
    d3.xhr2("http://localhost:8089/rest/resintos/"+codigo,"PUT",params,function(json) {
    });
    ListadoResintos();
  }