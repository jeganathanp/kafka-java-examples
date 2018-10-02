package examples.kafka.simple;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Example for simple kafka consumer
 */
public class SimpleKafkaConsumer {
    public static void main(String args[]){
        //Define the props
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"test");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        //Create kafka consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        //read it consumer from index
        kafkaConsumer.subscribe(Arrays.asList("test"));
        //close the consumer instance
        try{
            while(true){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for(ConsumerRecord<String, String> record: records){
                    System.out.println(record.value());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error in processing");
        }finally {
            kafkaConsumer.close();
        }
    }
}
