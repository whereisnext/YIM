 var tl;
        function onLoad() {
            var eventSource = new Timeline.DefaultEventSource();
            
            var zones = [
                {   start:    "Sat Jan 01 2012 00:00:00 GMT-0600",
                    end:      "Wed Jan 04 2012 00:00:00 GMT-0600",
                    magnify:  10,
                    unit:     Timeline.DateTime.MONTH
                }
            ];
            var zones2 = [
                 {   start:    "Sat Jan 01 2012 00:00:00 GMT-0600",
                    end:      "Wed Jan 04 2012 00:00:00 GMT-0600",
                    magnify:  10,
                    unit:     Timeline.DateTime.MONTH
                }
               
               
            ];
            
            var theme = Timeline.ClassicTheme.create();
            theme.event.bubble.width = 250;
            
            var date = "Sat Jan 01 2012 00:00:00 GMT-0600"
            var bandInfos = [
                Timeline.createHotZoneBandInfo({
                    width:          "90%", 
                    intervalUnit:   Timeline.DateTime.MONTH, 
                    intervalPixels: 220,
                    zones:          zones,
                    eventSource:    eventSource,
                    date:           date,
                    timeZone:       -6,
                    theme:          theme
                }),
                Timeline.createHotZoneBandInfo({
                    width:          "10%", 
                    intervalUnit:   Timeline.DateTime.YEAR, 
                    intervalPixels: 200,
                    zones:          zones2, 
                    eventSource:    eventSource,
                    date:           date, 
                    timeZone:       -6,
                    overview:       true,
                    theme:          theme
                })
            ];
            bandInfos[1].syncWith = 0;
            bandInfos[1].highlight = true;
            
            for (var i = 0; i < bandInfos.length; i++) {
                bandInfos[i].decorators = [
                    new Timeline.SpanHighlightDecorator({
                        startDate:  "Sat Jan 01 2012 00:00:00 GMT-0600",
                        endDate:    "Wed Jan 04 2012 00:00:00 GMT-0600",
                        color:      "", // set color explicitly
                        opacity:    50,
                        startLabel: "",
                        endLabel:   "",
                        theme:      theme
                    }),
                    new Timeline.PointHighlightDecorator({
                        date:       "Sat Jan 01 2012 00:00:00 GMT-0600",
                        opacity:    50,
                        theme:      theme
                        // use the color from the css file
                    }),
                    new Timeline.PointHighlightDecorator({
                        date:       "Wed Jan 04 2012 00:00:00 GMT-0600",
                        opacity:    50,
                        theme:      theme
                        // use the color from the css file
                    })
                ];
            }
            
            tl = Timeline.create(document.getElementById("tl"), bandInfos, Timeline.HORIZONTAL);
            tl.loadXML("http://176.34.61.2:8080/YIM/vancezhao.xml", function(xml, url) { eventSource.loadXML(xml, url); });
            
            setupFilterHighlightControls(document.getElementById("controls"), tl, [0,1], theme);
        }
        var resizeTimerID = null;
        function onResize() {
            if (resizeTimerID == null) {
                resizeTimerID = window.setTimeout(function() {
                    resizeTimerID = null;
                    tl.layout();
                }, 500);
            }
        }
