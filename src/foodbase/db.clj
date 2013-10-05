(ns foodbase.db)

;This contains only few foods for testing purposes

(def ingredientlist {
	:i-eb191222-108e-4826-a736-fa99faf29db6 {
		:name "pastöroitu kerma"
		:tags { :maitotuote true }
	}
	:i-fd7b1fa5-9069-4f85-ab96-1529ab6a83ef { :name "muunnettu maissitärkkelys" }
	:i-92524e01-16be-4b81-a05b-2ca6ac0bc4ea { :name "pektiini" }
	:i-b7bd971a-5e38-4f3d-94c1-bb537826942d { :name "hapate" }
	:i-292de23b-7620-4a7e-bda0-d088bd6c3742 { :name "sokeri" }
	:i-5de0e380-f2ad-4a8f-821e-3907a7202ffc { :name "glukoosisiirappi" }
	:i-a3114042-7335-4524-8434-3ac77fd58abf { :name "maissitärkkelys" }
	:i-1443790a-4a71-4813-8d66-b9d0e8d339eb { :name "stabilointiaine (E420)" }
	:i-fc0544f5-4992-453d-a49a-0f6dfcd587a2 { :name "liivate" }
	:i-8521e973-ace1-491a-bd68-fc1c969e681e { :name "muunnettu tärkkelys" }
	:i-bfbe800c-d1f8-435b-a91d-bad6fa833460 { :name "happamuudensäätöaine (E330, E331)" }
	:i-370b8b91-e9be-434b-9510-3dc6c9b0ab0a { :name "lakritsiuute" }
	:i-8f64bcd6-15fe-4c19-8969-6c092ba322db { :name "suola" }
	:i-2c9ad1cc-4dda-43d7-8695-c2f1fed369ba { :name "aromit" }
	:i-c287477a-72ca-476b-ab6f-41c0b552b45d { :name "pintakäsittelyaine (E903)" }
	:i-9c5bbf35-4fde-4cb6-a19e-2aa3dcc0fa59 { :name "värit (E153, E120, E160a, E131, E100)" }
	:i-47343e09-9952-4de9-9ec4-92feb56e5e64 { :name "pastöroitu maito" }
	:i-64adb208-d30c-4ec4-b484-6d43f2a973cf { :name "maitoproteiini" }
})

;Data structure for food item
(def foodlist {
	:f-3cfd0d6f-3d03-4908-9d98-b6ffe1783cac { ;ranskankerma
		:name "Kevyt Créme Fraíche eila laktoositon"
		:description "laktoositon kevyt ranskankerma keittoihin, kastikkeisiin ja wokkiruokiin, ..."
		:manufacturer "Valio"
		:barcode "6408430041213"
		:weight "200g"
		:origin "Finland"
		:ingredients {
			;uuid [order text]
			:i-eb191222-108e-4826-a736-fa99faf29db6 [1 "kerma"]
			:i-fd7b1fa5-9069-4f85-ab96-1529ab6a83ef [2 "muunnettu maissitärkkelys"]
			:i-92524e01-16be-4b81-a05b-2ca6ac0bc4ea [3 "sakeuttamisaine pektiini"]
			:i-b7bd971a-5e38-4f3d-94c1-bb537826942d [4 "hapate"]
		}
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
	:f-30e5f2a4-5263-4477-a337-857919de4308 { ;angrybirdskarkit
		:name "Angry Birds makeissekoitus"
		:description "makeissekoitus"
		:manufacturer "Fazer"
		:barcode "6416453036857"
		:weight "150g"
		:origin "Finland"
		:ingredients {
			:i-292de23b-7620-4a7e-bda0-d088bd6c3742 [1 "sokeri"]
			:i-5de0e380-f2ad-4a8f-821e-3907a7202ffc [2 "glukoosisiirappi"]
			:i-a3114042-7335-4524-8434-3ac77fd58abf [3 "maissitärkkelys"]
			:i-1443790a-4a71-4813-8d66-b9d0e8d339eb [4 "stabilointiaine (E420)"]
			:i-fc0544f5-4992-453d-a49a-0f6dfcd587a2 [5 "liivate"]
			:i-8521e973-ace1-491a-bd68-fc1c969e681e [6 "muunnettu tärkkelys"]
			:i-bfbe800c-d1f8-435b-a91d-bad6fa833460 [7 "happamuudensäätöaine (E330, E331)"]
			:i-370b8b91-e9be-434b-9510-3dc6c9b0ab0a [8 "lakritsiuute"]
			:i-8f64bcd6-15fe-4c19-8969-6c092ba322db [9 "suola"]
			:i-2c9ad1cc-4dda-43d7-8695-c2f1fed369ba [10 "aromit"]
			:i-c287477a-72ca-476b-ab6f-41c0b552b45d [11 "pintakäsittelyaine (E903)"]
			:i-9c5bbf35-4fde-4cb6-a19e-2aa3dcc0fa59 [12 "värit (E153, E120, E160a, E131, E100)"]
		}
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
	:f-80ad49f1-a416-4e04-b268-982a815f4fe7 { ;turkkilainenjogurtti
		:name "Turkkilainen jogurtti"
		:description "Turkkilainen jogurtti laktoositon maustamaton"
		:manufacturer "Arla Ingman Oy"
		:barcode "6413300016345"
		:weight "400g"
		:origin "Finland"
		:ingredients {
			:i-eb191222-108e-4826-a736-fa99faf29db6 [1 "kerma"]
			:i-47343e09-9952-4de9-9ec4-92feb56e5e64 [2 "pastöroitu maito"]
			:i-64adb208-d30c-4ec4-b484-6d43f2a973cf [3 "maitoproteiini"]
			:i-b7bd971a-5e38-4f3d-94c1-bb537826942d [4 "hapate"]
		}
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
})
