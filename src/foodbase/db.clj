(ns foodbase.db)

;This contains only few foods for testing purposes

(def ingredientlist [
	{
		:id "i-eb191222-108e-4826-a736-fa99faf29db6"
		:name "pastöroitu kerma"
		:tags { :maitotuote true }
	}
	{ :id "i-fd7b1fa5-9069-4f85-ab96-1529ab6a83ef" :name "muunnettu maissitärkkelys" }
	{ :id "i-92524e01-16be-4b81-a05b-2ca6ac0bc4ea" :name "pektiini" }
	{ :id "i-b7bd971a-5e38-4f3d-94c1-bb537826942d" :name "hapate" }
	{ :id "i-292de23b-7620-4a7e-bda0-d088bd6c3742" :name "sokeri" }
	{ :id "i-5de0e380-f2ad-4a8f-821e-3907a7202ffc" :name "glukoosisiirappi" }
	{ :id "i-a3114042-7335-4524-8434-3ac77fd58abf" :name "maissitärkkelys" }
	{ :id "i-1443790a-4a71-4813-8d66-b9d0e8d339eb" :name "stabilointiaine (E420)" }
	{ :id "i-fc0544f5-4992-453d-a49a-0f6dfcd587a2" :name "liivate" }
	{ :id "i-8521e973-ace1-491a-bd68-fc1c969e681e" :name "muunnettu tärkkelys" }
	{ :id "i-bfbe800c-d1f8-435b-a91d-bad6fa833460" :name "happamuudensäätöaine (E330, E331)" }
	{ :id "i-370b8b91-e9be-434b-9510-3dc6c9b0ab0a" :name "lakritsiuute" }
	{ :id "i-8f64bcd6-15fe-4c19-8969-6c092ba322db" :name "suola" }
	{ :id "i-2c9ad1cc-4dda-43d7-8695-c2f1fed369ba" :name "aromit" }
	{ :id "i-c287477a-72ca-476b-ab6f-41c0b552b45d" :name "pintakäsittelyaine (E903)" }
	{ :id "i-9c5bbf35-4fde-4cb6-a19e-2aa3dcc0fa59" :name "värit (E153, E120, E160a, E131, E100)" }
	{ :id "i-47343e09-9952-4de9-9ec4-92feb56e5e64" :name "pastöroitu maito" }
	{ :id "i-64adb208-d30c-4ec4-b484-6d43f2a973cf" :name "maitoproteiini" }
	{ :id "i-1b9ae59c-1ad6-4748-a011-4b12838c460f" :name "stabilointiaine karrageeni" }
])

;Data structure for food item
(def foodlist [
	{
		:id "f-3cfd0d6f-3d03-4908-9d98-b6ffe1783cac";ranskankerma
		:name "Kevyt Créme Fraíche eila laktoositon"
		:description "laktoositon kevyt ranskankerma keittoihin, kastikkeisiin ja wokkiruokiin, ..."
		:manufacturer "Valio"
		:barcode "6408430041213"
		:weight "200g"
		:origin "Finland"
		:ingredients [
			{ :id "i-eb191222-108e-4826-a736-fa99faf29db6" :order 1 :text "kerma" }
			{ :id "i-fd7b1fa5-9069-4f85-ab96-1529ab6a83ef" :order 2 :text "muunnettu maissitärkkelys" }
			{ :id "i-92524e01-16be-4b81-a05b-2ca6ac0bc4ea" :order 3 :text "sakeuttamisaine pektiini" }
			{ :id "i-b7bd971a-5e38-4f3d-94c1-bb537826942d" :order 4 :text "hapate" }
		]
		:nutritions {
			:unit "100g"
			:energy 140 ;kcal
			:proteins 2.8
			:carbs 6.5
			:lactose 0
			:fats 12
		}
		:tags {
			:laktoositon true
			:maitotuote true
		}
	} ;ranskankerma
	{
		:id "f-0b353afa-475d-4df7-994d-5eb1cb89da48";vispikerma
		:name "Vispikerma eila laktoositon"
		:description "laktoositon vispikerma"
		:manufacturer "Valio"
		:barcode "6408430014545"
		:weight ""
		:volume "2dl"
		:origin "Finland"
		:ingredients [
			{ :id "i-eb191222-108e-4826-a736-fa99faf29db6" :order 1 :text "kerma" }
			{ :id "i-1b9ae59c-1ad6-4748-a011-4b12838c460f" :order 2 :text "stabilointiaine karrageeni" }
		]
		:nutritions {
			:unit "100g"
			:energy 360 ;kcal
			:proteins 2.0
			:carbs 2.7
			:lactose 0
			:fats 38
		}
		:tags {
			:laktoositon true
			:maitotuote true
		}
	} ;vispikerma
	{
		:id "f-30e5f2a4-5263-4477-a337-857919de4308" ;angrybirdskarkit
		:name "Angry Birds makeissekoitus"
		:description "makeissekoitus"
		:manufacturer "Fazer"
		:barcode "6416453036857"
		:weight "150g"
		:origin "Finland"
		:ingredients [
			{ :id "i-292de23b-7620-4a7e-bda0-d088bd6c3742" :order 1 :text "sokeri" }
			{ :id "i-5de0e380-f2ad-4a8f-821e-3907a7202ffc" :order 2 :text "glukoosisiirappi" }
			{ :id "i-a3114042-7335-4524-8434-3ac77fd58abf" :order 3 :text "maissitärkkelys" }
			{ :id "i-1443790a-4a71-4813-8d66-b9d0e8d339eb" :order 4 :text "stabilointiaine (E420)" }
			{ :id "i-fc0544f5-4992-453d-a49a-0f6dfcd587a2" :order 5 :text "liivate" }
			{ :id "i-8521e973-ace1-491a-bd68-fc1c969e681e" :order 6 :text "muunnettu tärkkelys" }
			{ :id "i-bfbe800c-d1f8-435b-a91d-bad6fa833460" :order 7 :text "happamuudensäätöaine (E330, E331)" }
			{ :id "i-370b8b91-e9be-434b-9510-3dc6c9b0ab0a" :order 8 :text "lakritsiuute" }
			{ :id "i-8f64bcd6-15fe-4c19-8969-6c092ba322db" :order 9 :text "suola" }
			{ :id "i-2c9ad1cc-4dda-43d7-8695-c2f1fed369ba" :order 10 :text "aromit" }
			{ :id "i-c287477a-72ca-476b-ab6f-41c0b552b45d" :order 11 :text "pintakäsittelyaine (E903)" }
			{ :id "i-9c5bbf35-4fde-4cb6-a19e-2aa3dcc0fa59" :order 12 :text "värit (E153, E120, E160a, E131, E100)" }
		]
		:nutritions {
			:unit "100g"
			:energy 345 ;kcal
			:proteins 2.5
			:carbs 83
			:fats 0.2
		}
		:tags {
			:laktoositon true
			:maitotuote false
			:makeinen true
		}
	} ;angrybirdskarkit
	{
		:id "f-80ad49f1-a416-4e04-b268-982a815f4fe7" ;turkkilainenjogurtti
		:name "Turkkilainen jogurtti"
		:description "Turkkilainen jogurtti laktoositon maustamaton"
		:manufacturer "Arla Ingman Oy"
		:barcode "6413300016345"
		:weight "400g"
		:origin "Finland"
		:ingredients [
			{ :id "i-eb191222-108e-4826-a736-fa99faf29db6" :order 1 :text "kerma" }
			{ :id "i-47343e09-9952-4de9-9ec4-92feb56e5e64" :order 2 :text "pastöroitu maito" }
			{ :id "i-64adb208-d30c-4ec4-b484-6d43f2a973cf" :order 3 :text "maitoproteiini" }
			{ :id "i-b7bd971a-5e38-4f3d-94c1-bb537826942d" :order 4 :text "hapate" }
		]
		:nutritions {
			:unit "100g"
			:energy 125 ;kcal
			:proteins 4.4
			:carbs 4.4
			:carbs-sugars 4.4
			:fats 10
			:fats-saturated 6.6
			:salt 0.1
			:lactose 0
		}
		:tags {
			:laktoositon true
		}
	} ;turkkilainenjogurtti
])
