Pianito{
	*new{|synth=\default, dirt_s=nil, dirt_n=nil|
		^super.new.init(synth, dirt_s, dirt_n);
	}
	init{|synth, dirt_s, dirt_n|
		var win, keys, userview;
		var buttons,synths,tasks, releaseChangui;
		var blackpattern = [false, true, false, true, false, false, true, false, true, false, true, false];
		var step_acumulated = 0;
		var transpose_home = 24 + (12*3);
		var transpose = transpose_home;
		var slider_volume, slider_mod;
		var width = 355;
		var height = 90;
		var rootText = "C5";
		var letterNotes = ["C","C#","D","D#","E","F","F#","G","G#","A","A#","B"];
		var drawFunc, onClose, releaseAll;
		var color_bg = Color.fromHexString("212226");
		var color_bg_details = Color.fromHexString("838383");
		var color_key_white = Color.fromHexString("FFFFFF");
		var color_key_black = Color.fromHexString("3f4044");
		var color_key_on = Color.fromHexString("15cdcb");
		var sustainOn = false;
		var toggleOn = false;
		var sustain_keynumber = 32;
		var toggle_keynumber = 16777217; //32
		var btn_help;
		var pianitoType;
		var windowName;

		switch (synth.class,
			Synth, {
				pianitoType = \instance;
				windowName = "Instance of: " ++ synth.defName.asString;
			},
			SuperDirt, {

				if(dirt_s == nil,
					{
						dirt_s = \superchip;
					}
				);

				SuperDirt.default = synth;
				pianitoType = \superdirt;

				if(dirt_n == nil,
					{
						windowName = "SuperDirt Synth: " ++ dirt_s.asString;
					},
					{
						windowName = "SuperDirt Sample: " ++ dirt_s.asString ++ " " ++ dirt_n.asString;
					}
				);
			},
			{
				pianitoType = \synthdef;
				windowName = "Pianito: " ++ synth.asString;
			}
		);

		win = Window(windowName, Rect(128, 320, width, height), resizable: false);
		win.alwaysOnTop = true;

		rootText = StaticText(win, Rect(18, 56, 50, 25));
		rootText.string = "4 C";
		// Font.availableFonts.do{|font| font.postcs};
		Font.smoothing = true;
		Font.antiAliasing = true;
		rootText.font = Font("Roboto Medium", 12);
		rootText.stringColor = color_bg_details;




		// TODO: The button should open the help file
		/*btn_help = Button(win, Rect(width-36, 0, 20, 20));
		btn_help.string = "?";
		btn_help.canFocus = false;
		btn_help.action = {
			var help_win, help_text;
			help_win = Window.new("HELP", Rect(128, 320, 200, height), resizable: false);
			help_win.alwaysOnTop = true;
			help_text = StaticText(help_win, Rect(40, 0, 200, height));
			help_text.string = "[KEYS]: trigger notes\n[TAB]: toggle hold\n[SPACE]: sustain pedal\n[ARROWS]: transpose\n[SLIDER]: \\amp";
			help_win.front;
		};*/

		//this are Symbols, not Chars
		keys = [['z'],['s'],['x'],['d'],['c'],['v'],['g'],['b'],['h'],['n'],['j'],['m'],['q', ','],['2', 'l'],['w', '.'],['3',';'],['e','/'],['r'],['5'],['t'],['6'],['y'],['7'],['u'],['i'],['9'],['o'],['0'],['p'],['['],['='],[']']];


		buttons = Dictionary.new;
		synths = Dictionary.new;
		tasks = Dictionary.new;
		releaseChangui = Dictionary.new;

		keys.do{|keygroup, i|
			buttons[i.asSymbol] = false;
			releaseChangui[i.asSymbol] = true;
		};


		releaseAll = {
			var pos;
			keys.do{|keyArray, i|
				pos = i.asSymbol;

				if(buttons[pos].notNil){
					buttons[pos] = false;
				};
				if(tasks[pos].notNil){
					tasks[pos].stop;
				};
				if(synths[pos].notNil){
					synths[pos].release;
					synths[pos] = nil;
				};
			};
		};


		onClose = {arg view;
			releaseAll.value();
		};


		drawFunc = { arg view;
			var step_acumulated = 0;
			var space_w = 80;
			var space_h = 12;
			var toggle_size = 10;

			// Pen.fillColor_(color_bg);
			// Pen.fillRect( Rect( 0, 0, width, height ));

			Pen.width = 1;

			keys.do{|keygroup, i|
				var w = 15;
				var h = 18;
				var separation = 0;
				var step_x = w/2+separation;
				var step_y = 0;
				var btn;
				var color_base = color_key_white;
				var isBlack = blackpattern[i%blackpattern.size];
				var prevIsBlack;
				var pos = i.asSymbol;

				if(i == 0, {prevIsBlack = true}, {prevIsBlack = blackpattern[i-1%blackpattern.size]});

				if (isBlack == true, {
					color_base = color_key_black;
					step_x = w/2+separation;
					step_y = h+separation;

				},{
					if (prevIsBlack == false){
						step_x = w+separation;
					};
				});
				step_acumulated = step_acumulated + step_x;


				if(buttons[pos] == true){
					color_base = color_key_on;
				};

				Pen.fillColor_(color_base);
				Pen.strokeColor_(color_bg_details);
				Pen.fillRect( Rect(step_acumulated, 32-step_y,w,h));
				Pen.strokeRect( Rect(step_acumulated, 32-step_y,w,h));
			};

			Pen.fillColor_(if(sustainOn == true, {color_key_on}, {color_key_white}));
			Pen.strokeColor_(color_bg_details);
			Pen.fillRect(Rect(340/2 - (space_w/2) - 15, 55, space_w,space_h));
			Pen.strokeRect(Rect(340/2 - (space_w/2) - 15, 55, space_w,space_h));

			Pen.fillColor_(if(toggleOn == true, {color_key_on}, {color_key_white}));
			Pen.strokeColor_(color_bg_details);
			Pen.fillOval(Rect(0, 18, toggle_size,toggle_size));
			Pen.strokeOval(Rect(0, 18, toggle_size,toggle_size));

		};
		userview = UserView(win, Rect( 10, 10, width - 20, height - 20 ))
		.canFocus_( false )
		.drawFunc_( drawFunc)
		.onClose_(onClose);

		userview.animate = true;

		slider_volume = Slider(win, Rect(width-30, 0, 15, height));
		slider_volume.value = 0.7;
		slider_volume.canFocus = false;

		slider_mod = Slider(win, Rect(width-15, 0, 15, height));
		slider_mod.value = 0.5;
		slider_mod.canFocus = false;

		win.view.keyDownAction_{|view, char, mod, unicode, keycode, key|
			var pos;
			var transposeRoot = {|root, change|
				root = root + change;
				if(root < (12*2)){root = (12*2)};
				if(root > (12*8)){root = (12*8)};
				root;
			};

			char = char.toLower;
			char = char.asSymbol; //else is class Char


			switch (key,
				sustain_keynumber, {sustainOn = true},
				toggle_keynumber, {
					if(toggleOn == false,
						{
							toggleOn = true;
							keys.do{|keygroup, i| releaseChangui[i.asSymbol] = true}
						},
						{//else, release all notes
							toggleOn = false;
							//release all notes
							releaseAll.value();
						};
					);
				},
				16777232, { transpose = transpose_home},
				16777235, { transpose = transposeRoot.value(transpose, 12)},
				16777237, { transpose = transposeRoot.value(transpose,-12)},
				16777236, { transpose = transposeRoot.value(transpose,1)},
				16777234, { transpose = transposeRoot.value(transpose,-1)},
				{
					keys.do{|keyArray, i|
						keyArray.do{|element|
							if(element == char){
								pos = i.asSymbol;
							};
						}
					};


					// Scale.directory

					if(pos.notNil){

						buttons[pos] = true;

						if(synths[pos].isNil){
							var freq = (pos.asInteger+transpose).midicps;
							var degree = pos.asInteger+transpose-transpose_home;
							// for some reason default Tidal messages had latency:0.3, why? Less than 0.1 a latency log fest
							var latency = 0.1;

							switch (pianitoType,
								\instance, {
									// set the freq of a instance
									synth.set(\freq, freq);
									synths[pos] = 1;
								},
								\superdirt, {

									if(dirt_n.isNil,
										{
											//play a SuperDirt synth
											(type:\dirt, orbit:0, amp:slider_volume.value, sustain:slider_mod.value *0.8 + 0.01, s:dirt_s, n:degree, latency:latency).play;
										},
										{
											// play a SuperDirt sample
											(type:\dirt, orbit:0, amp:slider_volume.value, pan:slider_mod.value, s:dirt_s, n:dirt_n, speed:freq/262, latency:latency).play;
										}
									);

									synths[pos] = 1;
								},
								{
									// create a new Synth
									synths[pos] = Synth(synth,[\amp, slider_volume.value*0.3, \freq, freq, \mod, slider_mod.value]);
								}
							);

						};
				};};
			);


			rootText.string = letterNotes[transpose%letterNotes.size].asString ++ (transpose/12-1).asInteger.asString;
		};

		win.view.keyUpAction_{|view, char, mod, unicode, keycode, key|
			var pos;

			char = char.toLower;
			char = char.asSymbol; //else is class Char



			switch (key,
				sustain_keynumber, {
					sustainOn = false;
					//release all notes
					releaseAll.value();
				},
				{ // default
					if(sustainOn == false){ //release note if sustain is off
						keys.do{|keyArray, i|
							keyArray.do{|element|
								if(element == char){
									pos = i.asSymbol;
								};
							}
						};

						if(pos.notNil){

							var releaseNote = false;
							if (toggleOn == true,
								{
									if(releaseChangui[pos] == true,
										{
											releaseChangui[pos] = false;
										},
										{
											releaseChangui[pos] = true;
											releaseNote = true;
										}
									);
								},
								{ // else
									releaseNote = true;
								}
							);

							if(releaseNote == true,
								{
									//release note
									if(buttons[pos].notNil){
										buttons[pos] = false;

									};
									if(tasks[pos].notNil){
										tasks[pos].stop

									};
									tasks[pos]=Task({
										0.1.wait;
										if(synths[pos].notNil){
											synths[pos].release;
											synths[pos] = nil;
										};
										nil;
									});
									tasks[pos].play;
								}
							);
						}
					};
				};
			);
		};
		win.front;
	}
}
