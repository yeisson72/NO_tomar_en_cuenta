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

  function ActualizarPaquete() {
    var codigo = d3.select("#codigo").property("value");
    var remitente = d3.select("#remitente").property("value");
    var destinatario = d3.select("#destinatario").property("value");
    var resinto = d3.select("#resinto").property("value");
    var peso = d3.select("#peso").property("value");
    var cobro = d3.select("#cobro").property("value");
    var monto = d3.select("#monto").property("value");
    var params = "codigo="+codigo+"&remitente="+remitente+"&destinatario="+destinatario+
                 "&resinto="+resinto+"&peso="+peso+"&monto="+monto+
                 "&cobro="+cobro;
    d3.xhr2("http://localhost:8089/rest/paquetes/"+codigo,"PUT",params,function(json) {
    });
    ListadoPaquetes();
  }