package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.ApartadoWiki;
import com.example.demo.model.Comentario;
import com.example.demo.model.InfoWiki;
import com.example.demo.model.Noticia;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.ApartadoWikiRepository;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.repository.NoticiaRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class AndromedaApiApplication {

	@Autowired
	private PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(AndromedaApiApplication.class, args);
	}

	
	@Bean
	CommandLineRunner initData(ApartadoWikiRepository repositorioApartado, NoticiaRepository repositorioNoticia, UserRepository repositorioUsuario, PostRepository repositorioPost, ComentarioRepository repositorioComentario) {
		return args -> {
			List<InfoWiki> infoPlanetas = Arrays.asList(
					new InfoWiki("Planetas","Un planeta es un cuerpo celeste sin luz propia y de forma esférica que gira sobre sí mismo y comúnmente alrededor de una "
							+ "estrella (existen planetas sin Soles). Otra importante característica de un planeta, es su gravedad para “limpiar” o despejar su área "
							+ "circundante de objetos que se mantengan cercanos a su órbita de traslación." ,"http://drive.google.com/uc?export=view&id=12TlGu4mxBFH0GfqO0VZ8OlFEhk14ftHb"),
					new InfoWiki("mercurio", "Es el más cercano al Sol, a parte de ser el más pequeño de sus homólogos. Tiene parecido con la Tierra, "
							+ "ya que se compone de un 70% de elementos metálicos y un 30% restante de silicatos. Además, al igual que la Luna, Mercurio "
							+ "presenta una gran cantidad de crateres generados por el impacto de meteoritos. Mercurio cuenta con terrenos escarpados y lisos, así como acantilados, "
							+ "algunas de estas formaciones se extienden por varios cientos de kilómetros y tienen hasta 1 milla de altura. "
							+ "A diferencia de la Luna, Mercurio cuenta con zonas de hielo."
							+ "La temperatura de Mercurio durante el día pueden alcanzar los 430° Celsius y durante la noche -180° Celsius.", "https://drive.google.com/uc?export=view&id=1KY0lZWjkkME_zIOxt93qQPR9QojE0yV9"),
					new InfoWiki("venus", "A Venus es el segundo planeta más cercano al Sol. Dentro de los Planetas "
							+ "del Sistema Solar, Venus suele denominarse como el planeta “hermano de la Tierra” debido a su gran parecido, tanto en tamaño como "
							+ "en masa y su composición de tipo rocoso y terrestre. Pese a situarse más lejos del Sol que Mercurio, Venus posee la atmósfera más "
							+ "caliente del sistema solar, esto se debe a que está compuesta principalmente por gases de efecto invernadero, como el dióxido de carbono, "
							+ "atrapando mucho más calor del Sol. Actualmente carece de agua líquida y sus condiciones en superficie se consideran incompatibles con la vida conocida.", "https://drive.google.com/uc?export=view&id=1lz4oxCxYLN28lbA049LFWW6vj1OTU4xP"),
					new InfoWiki("tierra", "El planeta Tierra, nuestro planeta, es el mayor de los denominados planetas rocosos. Se formó hace unos 4600 millones de años y su nombre proviene del latín “Terra”, "
							+ "deidad griega que corresponde a la feminidad y fecundidad. El 71% de su composición corresponde a la hidrosfera (agua), hecho diferencial que ha permitido "
							+ "la existencia y persistencia de la vida humana. Ningún otro planeta del Sistema Solar contiene tal nivel de líquido."
							+ " Las propiedades físicas de la Tierra, la historia geológica y su órbita han permitido que la vida siga existiendo. Se estima que el planeta seguirá siendo capaz de sustentar "
							+ "vida durante otros 500 millones de años, ya que según las previsiones actuales, pasado ese tiempo la creciente luminosidad del Sol terminará causando la extinción de la biosfera.", "https://drive.google.com/uc?export=view&id=1atX4WR34h7ffVjTk1fCTIkhvu-mdrMN5"),
					new InfoWiki("marte", "Marte es el segundo de los planetas del Sistema solar de menor tamaño, después de Mercurio. Desde hace tiempo es comúnmente conocido como “planeta rojo”, fruto del color "
							+ "rojizo que adquiere por el óxido de hierro en la mayoría de su superficie. Su tamaño es casi la mitad del de la Tierra y su gravedad un 40% menor, lo cual lo hace prácticamente "
							+ "inhabitable según las últimas investigaciones de la NASA. Marte posee dos satélites pequeños y de forma irregular, Fobos y Deimos (hijos del dios griego Marte), que podrían ser asteroides capturados", "https://drive.google.com/uc?export=view&id=13I-FIQaugHG7Ny2vrcbJ6CABihRCZZUM"),
					new InfoWiki("jupiter", "El Planeta del Sistema Solar que recibe su nombre por el Dios Zeus de la mitología griega (Júpiter en mitología romana) es, precedido por el Sol, el planeta con mayor cuerpo celeste."
							+ " Tiene un tamaño de 1300 veces mayor que la Tierra. Como cuerpo masivo gaseoso, su composición está formada básicamente de hidrógeno y hielo. Como dato curioso, es considerado el planeta más antiguo del "
							+ "Sistema Solar, precediendo al Sol inclusive."
							+ "El planeta es conocido por una enorme formación meteorológica, la Gran Mancha Roja, fácilmente visible por astrónomos aficionados dado su gran tamaño, superior al de la Tierra. Su atmósfera está "
							+ "permanentemente cubierta de nubes que permiten trazar la dinámica atmosférica y muestran un alto grado de turbulencia.", "https://drive.google.com/uc?export=view&id=1syAgm__VLLVfb1L-0sA6l6m1Ezps2FU7"),
					new InfoWiki("saturno", "Es famoso este planeta del Sistema Solar por su imponente brillo procedente de sus anillos que rodean al planeta. Volviendo a Galileo, éste lo avistó por primera vez en el año 1610. "
							+ "Prácticamente todo el planeta (un 96%) está formado por hidrógeno y el 3% restante de hielo. Las partículas que componen los anillos de Saturno giran a una velocidad de 48.000 km/h, 15 veces más rápido que una bala.", "https://drive.google.com/uc?export=view&id=1tHq6tQ3RNDcOV_H4ThiacdMcHzL88_kT"),
					new InfoWiki("urano", "Este planeta está considerado el primero en ser descubierto mediante un telescopio. Su composición es muy parecida a la de sus hermanos Saturno y Júpiter, puesto que está formado por "
							+ "helio e hidrógeno, así como de agua, amoníaco y metano pero en cantidades mayores. Una peculiaridad de este planeta del Sistema Solar es su atmósfera, con las temperaturas más bajas de todo el Sistema, "
							+ "alcanzando la mínima de -224 grados Celsius. Asimismo, tiene una estructura de nubes muy compleja, acomodada por niveles, donde se cree que las nubes más bajas están compuestas de agua y las más altas de metano. "
							+ "En contraste, el interior de Urano se encuentra compuesto principalmente de hielo y roca.", "https://drive.google.com/uc?export=view&id=1p_IiFPtywSps8xuiansWe0urJlAtX--a"),
					new InfoWiki("neptuno", "Neptuno fue descubierto hace unos dos siglos por Urbain Le Verrier, John Couch y Johann Galle, allá por el año 1847. No obstante, algunos historiadores y astrónomos sostienen que el célebre Galileo "
							+ "Galilei ya observó este planeta por el año 1612, dato todavía sin confirmar. El planeta Neptuno está compuesto de roca fundida, agua, metano, hidrógeno, hielo y amoníaco líquido."
							+ "Neptuno es un planeta dinámico, con manchas que recuerdan las tempestades de Júpiter. La más grande, la Gran Mancha Oscura, tenía un tamaño similar al de la Tierra, pero en 1994 desapareció y se ha formado otra. "
							+ "Los vientos más fuertes de cualquier planeta del sistema solar se encuentran en Neptuno.", "https://drive.google.com/uc?export=view&id=1dXw9xzH8FQHDtfOMkOYTqf7y8wXddNEr")
					);
			List<InfoWiki> infoGalaxias = Arrays.asList(
					new InfoWiki ("Galaxias", "Mucho antes de que el Sistema Solar se formara, ya existían galaxias en el Universo. Este es un sistema que consta de múltiples elementos como las estrellas, los asteroides, los cuásares, los agujeros negros, "
							+ "los planetas, el polvo cósmico y las galaxias, entre muchos otros. Las galaxias son enormes grupos o sistemas espaciales compuestos por gases, polvo, estrellas y materia oscura, todo lo cual se mantiene unido mediante la gravedad. "
							+ "El número de galaxias es tremendamente grande y es por ahora imposible de saber exactamente. Los científicos han detectado miles de millones de galaxias en el universo observable, pero existe un número indeterminado ahí en donde los aparatos "
							+ "tecnológicos aún no alcanzan a llegar u observar.", "https://drive.google.com/uc?export=view&id=19Y8vSVWOSFPKnJTpsTSBRKb5O6RZPDnC"),
					new InfoWiki("elíptica", "Su estrechez a lo largo de un eje les confiere una apariencia elíptica. Se componen de las estrellas más viejas y suelen encontrarse en los cúmulos de galaxias. Las galaxias más grandes de las que se tiene conocimiento son elípticas, "
							+ "pero las hay también pequeñas.","https://drive.google.com/uc?export=view&id=1HlHVAPaF62lu5_DmL94pXiw-tMTUMdWy"),
					new InfoWiki("espiral", "Presentan una forma en espiral. Consisten en una especie de disco aplanado con “brazos” alrededor que le dan su forma, y en su parte media se concentra una gran cantidad de energía. Las estrellas, los planetas, "
							+ "el gas y el polvo giran alrededor del brillante centro. Las galaxias espirales barradas tienen “brazos” muy largos y el centro adquiere forma alargada, más parecida a una barra que a un círculo. Es justo en el centro donde se cree que nacen "
							+ "las estrellas.","https://drive.google.com/uc?export=view&id=11fI0rx2C5PYPxZHCrSdLgSlz32Qsi9d3"),
					new InfoWiki("irregular", " No tienen una morfología clara o distinguible y tienden a poseer estrellas jóvenes.", "https://drive.google.com/uc?export=view&id=1b9GYj_sHb_HFHp5hVk8A4y0cdQmy7cxu"),
					new InfoWiki("lenticular","Es una forma que se encuentra a medio camino entre la espiral y la elíptica. Consisten en un disco sin “brazos” con poca materia interestelar, "
							+ "aunque algunas pueden presentar cierta cantidad.", "https://drive.google.com/uc?export=view&id=15eI2Mpwr2aC1AYpAkwkdycFgkk08OR25" ),
					new InfoWiki("peculiar", " Como su nombre sugiere, son galaxias de forma extraña, inusual; o raras en términos de composición o tamaño.", "https://drive.google.com/uc?export=view&id=1EKqz6E9RYjP0GQ0L0wX3NYHjmjn-NmYY"),
					new InfoWiki("origen", "El origen de las galaxias es un tema aún discutido. Los astrónomos creen que comenzaron a formarse poco después del Bing Bang, la explosión cósmica que dio origen al Universo, según la teoría del mismo nombre. En la etapa posterior a "
							+ "la explosión, las nubes de gases se unieron y comprimieron debido a la gravedad, lo que constituyó una primera parte de las galaxias. Después, las estrellas pudieron concentrarse formando cúmulos globulares para dar paso a las galaxias, o quizá se "
							+ "formaron primero estas y las estrellas contenidas se agruparon más tarde. Dichas galaxias jóvenes eran más pequeñas que ahora y se localizaban más juntas unas de las otras, pero al colisionar entre sí y ser parte de la expansión del universo, crecieron "
							+ "y modificaron su forma.", "https://drive.google.com/uc?export=view&id=1ZzoRidF3tmyOXJPTsHfFsf3FivFZuhNY")
					);
			List<InfoWiki> infoSatelites = Arrays.asList(
					new InfoWiki("Satélites", "En astronomía, un satélite es un objeto que orbita (da vueltas) alrededor de un planeta. Hay centenares de satélites naturales, o lunas, en nuestro sistema solar, pero, desde 1957, también se han lanzado al espacio miles "
							+ "de satélites artificiales (fabricados por el hombre). Estos tienen usos muy diversos, como captar imágenes del Sol, la Tierra y otros planetas, o explorar el espacio para estudiar los agujeros negros, y las estrellas y galaxias remotas. También están "
							+ "los satélites de comunicaciones, los satélites meteorológicos y la Estación Espacial Internacional.", "https://drive.google.com/uc?export=view&id=1dKMq5epV6BaKr60rEvXAMe7WGhLpggZ2"),
					new InfoWiki("artificial", "Es cualquier objeto no natural que orbita alrededor de un cuerpo celeste. Estos objetos tienen una intención específica, pero todos persiguen el mismo fin: entender mejor el universo. A diferencia de los satélites naturales "
							+ "como la luna, los satélites artificiales son construidos por los seres humanos y se mueven en torno al objeto que es más grande que ellos. Generalmente son máquinas muy sofisticadas que se envían al espacio exterior, por lo que los desechos o restos "
							+ "de otras máquinas, las naves tripuladas, las estaciones orbitales y las sondas interplanetarias no son satélites artificiales.", "https://drive.google.com/uc?export=view&id=1oM2R35aIhi-9J4Y-dJrpa7Pqkdko9quk"),
					new InfoWiki("natural", "Un satélite natural es un cuerpo celeste que orbita alrededor de un planeta. Generalmente el satélite es más pequeño y acompaña al planeta en su órbita alrededor de su estrella madre. A diferencia de los fragmentos que orbitan formando un anillo, "
							+ "es el único cuerpo en su órbita. El término satélite natural se contrapone al de satélite artificial, siendo este último un objeto que gira en torno a la Tierra, la Luna o algunos planetas y que ha sido fabricado por el ser humano. En el caso de la Luna, que tiene una "
							+ "masa aproximada a 1/81 de la masa de la Tierra, podría considerarse como un sistema de dos planetas que orbitan juntos (sistema binario de planetas). Tal es el caso de Plutón y su satélite Caronte. Si dos objetos poseen masas similares, se suele hablar de sistema binario "
							+ "en lugar de un objeto primario y un satélite. El criterio habitual para considerar un objeto como satélite es que el centro de masas del sistema formado por los dos objetos esté dentro del objeto primario. El punto más elevado de la órbita del satélite se conoce como apoápside. "
							+ "Por extensión se llama lunas a los satélites de otros planetas. Se dice «los cuatro satélites de Júpiter», pero también, «las cuatro lunas de Júpiter». También por extensión se llama satélite natural o luna a cualquier cuerpo natural que gira alrededor de un cuerpo celeste, "
							+ "aunque no sea un planeta, como es el caso del satélite asteroidal Dáctilo que gira alrededor del asteroide Ida.", "https://drive.google.com/uc?export=view&id=1xVYO-VjMVlHI6de0ClEOUnKx8bbNN4Hg")
					
					);
			List<InfoWiki> infoEstrellas = Arrays.asList(
					new InfoWiki("Estrellas", "Las estrellas son motores de energía cósmica que producen calor, luz, rayos ultravioleta, rayos X y otras formas de radiación. Están compuestas casi en su totalidad de gas y plasma, un estado de supercalentamiento de la materia compuesta de partículas subatómicas."
							+ " Aunque la estrella más conocida, el Sol, existe en solitario, tres de cada cuatro estrellas existen como parte de un sistema binario compuesto por dos estrellas orbitando mutuamente. Nadie sabe cuántas estrellas existen, pero podrían alcanzar un número extraordinario. Nuestro"
							+ " universo podría albergar más de 100 000 millones de galaxias, y cada una de ellas podría tener más de 100 000 millones de estrellas. Tan sólo en una noche clara, desde la Tierra pueden observarse alrededor de 3000 estrellas a simple vista. Los humanos de diferentes culturas han "
							+ "dibujado el cielo mediante estas estrellas.", "https://drive.google.com/uc?export=view&id=1AqsjrQBjWXD8gWsaSM9_B7znDL8MgmVe"),
					new InfoWiki("apariencia", "Algunas estrellas han sobresalido siempre del resto. Su brillo es un factor de cuanta energía despiden, lo cual se conoce como su luminosidad, y también la distancia a la que se encuentran de la Tierra. Las estrellas del cielo también pueden parecer de diferentes"
							+ " colores porque sus temperaturas no son iguales. \n \n Las estrellas calientes son blancas o azules, mientras que las más frías parecen tener tonos rojos o anaranjados. Un par de prismáticos de tamaño 7x50 puede ser ideal para observar las estrellas. Si prefieres los telescopios, los refractores "
							+ "y los reflectores, que enfocan la luz estelar con espejos, son los más comunes. Que tengan lentes de alta calidad y un conjunto de tres oculares que puedas usar para cambiar el aumento son las características más importantes para poder observar tranquilamente "
							+ "las estrellas.", "https://drive.google.com/uc?export=view&id=1fj4CJcd0KSuo4ZVrnTVCOODm8uj5Ce0F"),
					new InfoWiki("ciclo de vida", "Las estrellas jóvenes se llaman en esta fase protoestrellas. A medida que se desarrollan acumulan masa de las nubes que las rodean y crecen en lo que se denomina estrella en la secuencia principal. Las estrellas en secuencia principal, como el Sol, existen en "
							+ "un estado de fusión nuclear durante el cual emitirán energía durante miles de millones de años, mientras convierten el hidrógeno a helio. Las estrellas evolucionan durante miles de millones de años. Cuando finaliza su fase de secuencia principal pasan a través de otros estados de "
							+ "existencia en función de su tamaño y otras características. Cuanto mayor sea la masa de una estrella, menor será su intervalo de vida. A medida que las estrellas avanzan hacia el final de sus vidas, gran parte de su hidrógeno se ha convertido en helio. El helio se hunde en el "
							+ "núcleo de la estrella aumentando su temperatura y provocando la expansión de su capa exterior. Estas estrellas grandes e hinchadas se conocen como gigantes rojas.", "https://drive.google.com/uc?export=view&id=1jCIdRjm9iJYF_DFLk-Z5PGeqE4izJabG"),
					new InfoWiki("sol", "Comparado con las mil millones de estrellas del universo, el Sol pasa desapercibido. Sin embargo, para la Tierra y otros planetas de alrededor, el Sol es un poderoso centro de atención: su luz da vida, calor y mantiene unido el sistema solar. A pesar de bañar de luz y calor "
							+ "el planeta Tierra y, por tanto, ser nuestra fuente de vida, el Sol aún oculta grandes misterios que aún no hemos logrado comprender. El Sol es una estrella enorme. Con un diámetro de 1,4 millones de kilómetros podría albergar a 109 planetas en su superficie. Si fuera hueco, más de un "
							+ "millón de Tierras podrían vivir en su interior, pero no lo es. Está relleno de gases calientes que representan más del 99,8 por ciento de la masa total del sistema solar. ¿A qué llamamos caliente? La temperatura alcanza los 5500 grados centígrados en la superficie y más de 15,5 millones "
							+ "de grados centígrados en el núcleo. En el núcleo del Sol, se producen reacciones de fusión en las que el hidrógeno se transforma en helio, que genera la energía. Unas pequeñas partículas de luz llamadas fotones transportan esta energía a través de la zona radiante hasta la capa superior "
							+ "del interior del Sol, la zona convectiva. Ahí, el movimiento de los gases hirviendo (como en una lámpara de lava) lleva la energía a"
							+ " la superficie. Este viaje dura más de un millón de años.", "https://drive.google.com/uc?export=view&id=1Fd9HBdNuKC0jmXdN33pPMg-UwmSuuC37")	
					);
					ApartadoWiki planetas = new ApartadoWiki("Planetas");
					planetas.setInfo(infoPlanetas);
					ApartadoWiki galaxias = new ApartadoWiki("Galaxias");
					galaxias.setInfo(infoGalaxias);
					ApartadoWiki satelites = new ApartadoWiki("Satélites");
					satelites.setInfo(infoSatelites);
					ApartadoWiki estrellas = new ApartadoWiki("Estrellas");
					estrellas.setInfo(infoEstrellas);
			repositorioApartado.saveAll( Arrays.asList(planetas, galaxias, satelites, estrellas));
					Noticia not1 = new Noticia ("https://drive.google.com/uc?export=view&id=1CUtuqaEO2PKYLM2-oWuWToTOSo6snqQn","¿Cuándo podremos anunciar el hallazgo de vida extraterrestre?","https://elpais.com/ciencia/vacio-cosmico/2022-02-09/cuando-podremos-anunciar-el-hallazgo-de-vida-extraterrestre.html");
					Noticia not2 = new Noticia ("https://drive.google.com/uc?export=view&id=1Dcblln1UWW0504tkOsNLFKkfpY4aohRZ","El rover ‘Curiosity’ no ha encontrado vida en Marte, pero eso hay que demostrarlo","https://elpais.com/ciencia/2022-02-08/el-rover-curiosity-no-ha-encontrado-vida-en-marte-pero-eso-hay-que-demostrarlo.html");
					Noticia not3 = new Noticia ("https://drive.google.com/uc?export=view&id=1k2-aYPpbG7GMt5nGALUufmDuNcyi-C2Q","Confirman la existencia de un segundo asteroide troyano terrestre de más de un kilómetro de diámetro","https://www.20minutos.es/noticia/4950151/0/confirman-la-existencia-de-un-segundo-asteroide-troyano-terrestre-de-mas-de-un-kilometro-de-diametro/");
					Noticia not4 = new Noticia ("https://drive.google.com/uc?export=view&id=1mUlg8vyMsdTfyTUcSU8p7aMWjG4y_YIS","Descubren un misterioso objeto cósmico que emite gigantescas ráfagas de energía tres veces a la hora","https://www.20minutos.es/noticia/4947391/0/descubren-un-misterioso-objeto-cosmico-que-emite-gigantescas-rafagas-de-energia-tres-veces-a-la-hora/");
					Noticia not5 = new Noticia ("https://drive.google.com/uc?export=view&id=12w4C0-XCDv_yMlPan6LB9ClMuTx5bcK6","Viajar a la Luna de nuevo en 2022: la NASA y otras empresas privadas ya preparan a sus astronautas","https://www.20minutos.es/noticia/4953606/0/viajar-luna-2022-nasa-empresas-privadas-astronautas/?autoref=true");
					Noticia not6 = new Noticia ("https://drive.google.com/uc?export=view&id=1NNbprixsm9suOzS1l_ILTH3F3_F5J1rl","El origen de las moléculas de un meteorito marciano hallado en la Antártida no es biológico","https://www.20minutos.es/noticia/4940738/0/origen-moleculas-meteorito-marte-antartida-biologico/");
			repositorioNoticia.saveAll(Arrays.asList(not1,not2,not3,not4,not5,not6));
			
			User us1 = new User("F123",encoder.encode("123"), "fran@host.com", "Fran", "Dueño de la página a jornada completa. Si necesitas ayuda contacta conmigo mediante el formulario de contacto. Agradecería que se me informe sobre bugs en la página para solventarlos lo antes posible. ");
			us1.setNumberOfPosts(2);
			User us2 = new User("J123",encoder.encode("11111111"), "jorge@host.com", "Jorge", "Profesor de entorno servidor");
			us2.setNumberOfPosts(1);
			us2.setNumberOfComents(1);
			User us3 = new User("A123",encoder.encode("11111111"), "anda@host.com", "Ana", "Desarrolladora java interesada en astronomía");
			us3.setNumberOfPosts(1);
			us3.setNumberOfComents(1);
			repositorioUsuario.saveAll(Arrays.asList(us1,us2,us3));
			
			Post p1 = new Post("La astrología es increible", "Lorem ipsum dolor sit amet, consectetur adipiscing . Vestibulum at fermentum ipsum. Ut in leo eu nunc tincidunt "
					+ "consectetur. Suspendisse imperdiet nisl sit  sapien aliquam iaculis. Nunc eget sem neque. Sed dui metus, volutpat et augue non, mollis scelerisque diam. "
					+ "Suspendisse hendrerit lacinia lacus id eleifend. Aenean id velit , ullamcorper eros efficitur, tristique mauris. Suspendisse hendrerit eget velit non "
					+ "dictum. Phasellus et dapibus mauris. Suspendisse tincidunt tortor mauris,  volutpat enim lacinia sed. In hac habitasse platea dictumst. Phasellus at orci "
					+ "ligula. Etiam suscipit ut nulla at . Proin diam purus, viverra id urna nec, pellentesque facilisis ex.", us1);
			Post p2 = new Post("Viajando por Europa para ver la aurora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum fermentum ipsum. Ut in leo eu nunc tincidunt "
					+ "consectetur. Suspendisse imperdiet nisl sit amet sapien aliquam iaculis. Nunc eget sem neque. Sed dui metus, volutpat et augue non, mollis scelerisque diam. "
					+ "Suspendisse hendrerit lacinia lacus id eleifend.  id velit semper, ullamcorper eros efficitur, tristique mauris. Suspendisse hendrerit eget velit non "
					+ "dictum. Phasellus et dapibus mauris. Suspendisse tincidunt tortor mauris, vel volutpat enim lacinia sed. In hac habitasse platea dictumst. Phasellus at orci "
					+ "ligula. Etiam suscipit ut nulla at ornare. Proin  purus, viverra id urna pellentesque facilisis ex.", us1);
			Post p3 = new Post("¿Dónde puedo observar la lluvia de estrellas del 22 de Mayo?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at fermentum ipsum. Ut in leo eu nunc tincidunt "
					+ "consectetur. Suspendisse imperdiet nisl sit amet sapien  iaculis. Nunc eget sem neque. Sed dui metus, volutpat et augue non, mollis scelerisque diam. "
					+ "Suspendisse hendrerit lacinia lacus id eleifend. Aenean id velit semper, ullamcorper eros efficitur, tristique mauris. Suspendisse hendrerit eget velit non "
					+ "dictum. Phasellus et dapibus mauris. Suspendisse tincidunt tortor mauris, vel volutpat enim lacinia sed. In hac habitasse platea dictumst. Phasellus at orci "
					+ "ligula. Etiam suscipit ut nulla at ornare. Proin diam purus, viverra id urna nec, pellentesque facilisis ex.", us2);
			Post p4 = new Post("La NASA anuncia nuevos descubrimientos ¿Son interesantes?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at fermentum ipsum. Ut in leo eu nunc tincidunt "
					+ "consectetur. Suspendisse imperdiet nisl sit amet sapien aliquam iaculis. Nunc eget sem neque. Sed dui metus, volutpat et augue non, mollis scelerisque diam. "
					+ "Suspendisse hendrerit lacinia lacus id eleifend. Aenean id velit semper, ullamcorper eros efficitur, tristique mauris. Suspendisse hendrerit eget velit non "
					+ "dictum. Phasellus et dapibus mauris. Suspendisse tortor mauris, vel volutpat enim lacinia sed.  hac habitasse platea dictumst. Phasellus at orci "
					+ "ligula. Etiam suscipit ut nulla at ornare. Proin diam purus, viverra id urna nec, pellentesque facilisis ex.", us3);
			Comentario c1 = new Comentario ("Buen post compañero, ojalá saber latín y entenderlo", us3);
			Comentario c2 = new Comentario ("Jajajajajajajaja me encantó el segundo párrafo", us2);
			p1.addComentario(c1);
			p1.addComentario(c2);
			repositorioPost.saveAll(Arrays.asList(p1,p2,p3,p4));
			 
		};
	}
}
