 /**
   *增加模板行
   */
   
function addRow() {
    var table = document.getElementById("addTable");
    var tbody = document.getElementById("templeteTBody");
    var newTBody = tbody.cloneNode(true);
    newTBody.style.display="";
    var footTBody = document.getElementById("footTbody");
    return table.insertBefore(newTBody,footTBody);
   }
/**
   *删除模板行
   */
   
function deleteRow(obj) {
    var tbody = obj.parentNode.parentNode.parentNode;
    var table = document.getElementById("addTable"); 
    table.removeChild(tbody);
   }

/**
   *向模板中填充值
   */
function setValue(){

            var tbody=addRow();

    }

//简化document.getElementById()方法
function $(obj) {
  return document.getElementById(obj);
}
//删除行
function delRow(e) {
    var evt = e || event;
    var targetTable = $("addTable");
    targetTable.deleteRow(~~evt.target.parentNode.parentNode.cells[0].innerHTML);
    for (var i = 0; i<targetTable.rows.length; i++) {
        if (i != 0)
        targetTable.rows[i].cells[0].innerHTML = i;
    }
}
 
function addRow() {
    var targetTable = $("addTable");
    var index = targetTable.rows.length;
    var newRow = targetTable.insertRow(index);
    newRow.innerHTML = '<tr class="input"><td>' + index + '</td>'
   +'<td><textarea class="form-control" rows="6" placeholder="Question '+ index + ' Content"></textarea></td>'
   +'<td><input type="text" class="form-control" placeholder="10"></td>'
   +'	<td>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox1" value="option1"> 2'
   +'			</label>'
   +'	</div>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox2" value="option2"> 2'
   +'			</label>'
   +'	</div>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox3" value="option3"> 3'
   +'			</label>'
   +'	</div>'
   +'		<td>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox1" value="option1"> Knowledge'
   +'			</label>'
   +'	</div>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox2" value="option2"> Analysis'
   +'			</label>'
   +'	</div>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox3" value="option3"> Application'
   +'			</label>'
   +'	</div>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox4" value="option1"> Comprehension'
   +'			</label>'
   +'	</div>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox5" value="option2"> Synthesis'
   +'			</label>'
   +'	</div>'
   +'		<div class="checkbox">'
   +'			<label>'
   +'			<input type="checkbox" id="inlineCheckbox6" value="option3"> Evaluation'
   +'			</label>'
   +'	</div>'
   +'		</td>'
   +'		<td><button type="button" class="btn btn-default" onclick="delRow(event)">'
   +'			<span class="glyphicon glyphicon-remove"></span></button></td>'; 
}