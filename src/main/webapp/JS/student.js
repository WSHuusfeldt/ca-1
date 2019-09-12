window.onload = function () {
        
    
    var btn = this.document.getElementById("refresh");
    btn.onclick = function () {
        start("/jpareststarter/api/groupmembers/all");
    }
    
    document.getElementById("output").addEventListener("load", start("/jpareststarter/api/groupmembers/all"));
    
    function start(url) {
        fetch(url)
            .then(res => res.json()) 
            .then(data => {
                document.getElementById("output").innerHTML =
                data.map(x =>
                    "<tr><td>" + x.name + '</td>' +
                    "<td>" + x.studentID + '</td>' +
                    "<td>" + x.color + '</td></tr>')
            })
    }

}

