import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.rest.util.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.Instant;

public class main {

        public static void main (final String[]args ){


            final String token = "OTUzNjM2NDM1OTM4OTMwNzM5.YjHdJA.pnKFFZkZhzwx6UEPDpo7Dwsmdbg";
            final DiscordClient cliente = DiscordClient.create(token);
            final GatewayDiscordClient puerto = cliente.login().block();
// se crea la clase y se implementa al bot.

            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                    .color(Color.GREEN)
                    .title("El doritos")
                    .image("attachment://todd.png")
                    .build();

            puerto.on(MessageCreateEvent.class).subscribe(event -> {
                //Se crea el evento de recibir un mensaje
                final Message message = event.getMessage();
                // se crea una igualdad con el mensaje recibido y buscando el canal en el que se escribió se devuelve el mensaje en ese canal
                if ("!hey".equals(message.getContent())) {
                    final MessageChannel channel = message.getChannel().block();

                    channel.createMessage("Buenos dias").block();
                }

                try {
                    DriveQuickstart.main();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }

                if("!Help".equals(message.getContent())){
                    String IMAGE_URL ="https://c.tenor.com/HG5nbp_7N_QAAAAd/triangle-shape.gif";
                    String ANY_URL = "https://www.youtube.com/watch?v=C0HGLDZu9Zg&ab_channel=NikkoCana";
                    final MessageChannel channel = message.getChannel().block();
                    EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
                    builder.author("Yo", ANY_URL, IMAGE_URL);
                    builder.image(IMAGE_URL);
                    builder.title("Info");
                    builder.url(ANY_URL);
                    builder.description("Descripcion ");
                    builder.thumbnail(IMAGE_URL);
                    builder.footer("setFooter --> setTimestamp", IMAGE_URL);
                    builder.timestamp(Instant.now());
                    channel.createMessage(builder.build()).block();
                }

                if ("!Teletubbie".equals(message.getContent())) {
                    final MessageChannel channel = message.getChannel().block();

                    InputStream fileAsInputStream = null;
                    try {
                        fileAsInputStream = new FileInputStream("C:\\Users\\alex0\\OneDrive\\Escritorio\\todd.png");
                    } catch (FileNotFoundException e) {
                        System.out.println("Error lectura de fichero" + e.getMessage());
                    }
                    ;
                    channel.createMessage(MessageCreateSpec.builder()
                            .content(" ")
                            .addFile("Pou_tactico.jpg", fileAsInputStream)
                            .addEmbed(embed)
                            .build()).subscribe();
                }

                if("!Img".equals(message.getContent())){
                    final MessageChannel channel = message.getChannel().block();

                    InputStream fileAsInputStream = null;
                    try{
                        fileAsInputStream = new FileInputStream("C:\\Users\\alex0\\your_aux.jpeg");
                    } catch (FileNotFoundException e) {
                        System.out.println("Error de lectura "+e.getMessage());;
                    }

                    channel.createMessage(MessageCreateSpec.builder()
                            .content("  ")
                            .addFile("your_aux.jpeg",fileAsInputStream)
                            .addEmbed(embed)
                            .build()).subscribe();
                }

                if("!/pdf".equals(message.getContent())){
                    final MessageChannel channel = message.getChannel().block();

                    InputStream fileAsInputStream = null;
                    try{
                        fileAsInputStream = new FileInputStream("C:\\Users\\alex0\\Downloads\\examen.pdf");
                    } catch (FileNotFoundException e) {
                        System.out.println("Error en la descarga del archivo " +e.getMessage());
                    }


                }
            });



            puerto.onDisconnect().block();
        }
    }

