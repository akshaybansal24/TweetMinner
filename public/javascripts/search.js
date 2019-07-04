;(function(window) {

	'use strict';

	// Setting global class name
    document.documentElement.className = 'js';

	// Submit called on form submit
    jQuery('#search').submit(function (e) {
        e.preventDefault();
        
        if($('#search-key').val() != ''){
        	
        	var searchKey = $('#search-key').val();
        	searchKey = searchKey.replace(/\s/g,'_');
        	var searchResultDiv = "<div class='heading-center' id="+searchKey+"></div>";
        	jQuery("#search-body").append(searchResultDiv);
        	//console.log(searchResultDiv);
        	jQuery.get("http://localhost:9000/getTweetsEmotion/" +  $('#search-key').val()).done(function (data) {
        		var searchKey = $('#search-key').val();
            	searchKey = searchKey.replace(/\s/g,'_');
//            	console.log(JSON.stringify(data[0]));
            	var tweetTableId = searchKey + "Table";
            	var tweetHeading = "<h1 align='center'><a target='_blank' href='http://localhost:9000/getTweetWordCount/"+$('#search-key').val()+"'>"+$('#search-key').val()+ "</a>&nbsp&nbsp"+data[0].TweetEmotion+"</h1><br>"+"<table style='table-layout: fixed;width:100%' id=" +tweetTableId+"></table>";
            	jQuery("#"+searchKey).append(tweetHeading);
            	websocketFunction($('#search-key').val());
            });
        	
        	
        }
        
        
        
        /*if( $('#search-key').val() != '' ){
            var outerdom = "<p class='header'>Keyword:- "+$('#search-input').val()+"</p>"+"<div class='tweet-box' id="+$('#search-input').val()+"></div>";
            jQuery(".tweets-body").append(outerdom);
            var ws = new WebSocket('ws://localhost:9000/connectWebSocket');

            ws.onopen = function(msg) {
                ws.send($('#search-key').val());
            };

            ws.onmessage = function(msg) {
            	
                var parsed = JSON.parse(msg.data);
                console.log(JSON.stringify(parsed));
                parsed.forEach(function (value) {
                    var dom = "";
                    dom += "<p>" + " " + "*" + " ";
                    dom += "<a target='_blank' href='http://localhost:9000/userProfile/"+value.screenName+"'>("+value.name+")</a>" + "Tweet - "+value.text;
                    dom += "</p>";
                    jQuery("#"+value.message).append(dom);
                    jQuery("#"+value.message).animate({ scrollTop: $("#"+value.message).prop("scrollHeight")}, 500);
                });
            };

            ws.onclose = function(msg) {
                // Logic for closed connection
                console.log('Connection was closed.');
            };
            ws.error =function(err){
                // Write errors to console
                console.log(err);
            }
		}*/
    });
    
    var websocketFunction = function(searchKey){
    	var webSoc = new WebSocket('ws://localhost:9000/connectWebSocket');
    	
    	webSoc.onopen = function(message){
    		webSoc.send($('#search-key').val());
    	};
    	
    	webSoc.onmessage = function(message){
    		console.log("here");
    		var parsedResult = JSON.parse(message.data);
//    		console.log(JSON.stringify(parsedResult));
    		
    		var tableData = "<tr>";
    		tableData += "<th>S.No.</th>"; 
    		tableData += "<th>Name</th>";
    		tableData += "<th>Location</th>";
    		tableData += "<th>Tweet</th>";
    		tableData += "<th>Hash Tags</th>";
    		tableData += "</tr>";
    		for(var index = 0;index<parsedResult.length-1;index++){
    			var indexNo = index+1;
    			tableData += "<tr>";
    			tableData += "<td width='10%'>"+ indexNo + "</td>";
    			tableData += "<td width='20%'><a target='_blank' href='http://localhost:9000/getUserProfile/"+ parsedResult[index].displayName+"'>"+parsedResult[index].name + "</a></td>";
    			if(parsedResult[index].showLocationLink)
    				tableData += "<td width='20%'><a target='_blank' href='http://localhost:9000/getTweetsByLocation/"+parsedResult[index].location+"/" + parsedResult[index].latitude+"/"+parsedResult[index].longitude+"'>" +parsedResult[index].location+ "</a></td>";
    			else
    				tableData += "<td width='20%'>" + parsedResult[index].location + "</td>";
    			tableData += "<td width='30%'>" + parsedResult[index].tweet + "</td>";
    			tableData += "<td width='20%'>";
    			var hashTags = parsedResult[index].hashTags;
    			if(hashTags || hashTags!=="" || hashTags!=null){
    				var hashTagSplit = hashTags.split(",");
    				for(var hashTagIndex=0; hashTagIndex<hashTagSplit.length; hashTagIndex++){
    					tableData += "<a target='_blank' href='http://localhost:9000/getHashTagTweets/"+hashTagSplit[hashTagIndex]+"'>"+hashTagSplit[hashTagIndex] + "</a>&nbsp";
    				}
    			}
    			tableData += "</td></tr>";
    		}
    		//tableData += "</tbody>";
    		var searchKey = parsedResult[parsedResult.length-1].searchKey.replace(/\s/g,'_');
    		var tableId = searchKey + "Table";
    		//console.log(tableId);
    		//console.log(tableData);
    		jQuery("#"+tableId).html('').append(tableData);
    		console.log("chal ja")
    	};
    	
    	webSoc.onclose = function(message) {
            // Logic for closed connection
            console.log('Connection was closed.');
        };
        webSoc.error =function(err){
            // Write errors to console
            console.log(err);
        }
    }

})(window);
