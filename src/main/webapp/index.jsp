<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OpenAPI - 대기오염지수</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
    <div class="p-5" align="center">
        <h2 class="p-2 bg-primary bg-opacity-25">실시간 대기오염 정보</h2>
        <br><br>
        <div class="input-group">
            <span class="input-group-text bg-dark text-white">지역</span>
            <select id="location" class="form-select w-25">
                <option>서울</option>
                <option>인천</option>
                <option>경기</option>
                <option>강원</option>
                <option>부산</option>
            </select>
            <button id="btn1" class="btn btn-dark">대기오염 정보 보기</button>
        </div>
        <br><br>
        <table class="table table-hover table-striped text-center">
            <thead>
                <tr class="table-dark">
                    <th>측정소</th>
                    <th>측정일시</th>
                    <th>통합대기환경수치</th>
                    <th>미세먼지농도</th>
                    <th>일산화탄소농도</th>
                    <th>이산화질소농도</th>
                    <th>아황산가스농도</th>
                    <th>오존농도</th>
                </tr>
            </thead>
            <tbody id = "content">
            	
            </tbody>
        </table>
    </div>
    <script>
    	$(function() {
    		$('#btn1').click(
    			function() {
    				$.ajax({
    	    			url: 'air.do',
    	    			type: 'get',
    	    			data: {location: $('#location').val()},
    	    			dataType: 'json',
    	    			success(result) {
    	    				console.log(result);
    	    				$('#content').text('');
    	    				
    	    				for(let i of result) {
    	    					console.log(i);
    	    					let row = "<tr>";
    	    			        for (let key in i) {
    	    			            if (i.hasOwnProperty(key)) {
    	    			                row += "<td>" + i[key] + "</td>";
    	    			            }
    	    			        }
    	    			        row += "</tr>";
    	    			        $('#content').append(row);
    	    			        
    	    					/* $('#content').append(
    	    							"<tr>" +
    	    							  "<td>"+ i["stationName"] + "</td>"
    	    							+ "<td>"+ i["dataTime"] + "</td>"
    	    							+ "<td>"+ i["khaiValue"] + "</td>"
    	    							+ "<td>"+ i["pm10Value"] + "</td>"
    	    							+ "<td>"+ i["so2Value"] + "</td>"
    	    							+ "<td>"+ i["coValue"] + "</td>"
    	    							+ "<td>"+ i["no2Value"] + "</td>"
    	    							+ "<td>"+ i["o3Grade"] + "</td>"
    	    							+ "</tr>"
    	    							
    	    					) */
    	    					
    	    				}
    	    			}
    	    		});
    			}	
    		);
    		
    	})
    </script>
</body>
</html>