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

  function ActualizarHistorial() {
    var codigo = d3.select("#codigo").property("value");
    var persona = d3.select("#persona").property("value");
    var fecha = d3.select("#fecha").property("value");
    var descripcion = d3.select("#descripcion").property("value");
    var paquete = d3.select("#paquete").property("value");
    var params = "codigo="+codigo+"&persona="+persona+"&fecha="+fecha+
                 "&descripcion="+descripcion+"&paquete="+paquete;
    d3.xhr2("http://localhost:8089/rest/historiales/"+codigo,"PUT",params,function(json) {
    });
    ListadoHistoriales();
  }