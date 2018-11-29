package com.demo.musicapp.musicApp;

import com.demo.musicapp.musicApp.dao.TagRepo;
import com.demo.musicapp.musicApp.model.Playlist;
import com.demo.musicapp.musicApp.model.Tag;
import com.demo.musicapp.musicApp.trie.Node;
import com.demo.musicapp.musicApp.trie.Trie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
public class MusicAppRestController {

    Trie trieOcc = new Trie();
    Trie triePLRef = new Trie();
    List<NodeTuple> tagList =  new ArrayList <>();
    List<NodeTuple> tagListPredicted =  new ArrayList <>();
    protected  List<String> Tags = Arrays.asList("Soft Rock", "Hard Rock", "Rock", "Alternative", "Pop", "Blues", "Classical", "EDM", "Techno", "Trance", "Electronic", "Progressive", "Down Tempo", "Hip Hop", "Rap", "European", "Arabic");
    List<String> matchedTags = new ArrayList <>();
    List<NodeTuple> relevantPlaylists =  new ArrayList <>();

    @Autowired
    TagRepo tagRepo;

    @PostConstruct
    public void init(){

        Iterable<Tag> tagItr = tagRepo.findAll();
        for(Tag tag : tagItr){
           // System.out.println(" tag.getTagName()==========="+tag.getTagName()+"     tag.getPlaylists().size()=====>>>"+tag.getPlaylists().size());
            trieOcc.add(tag.getTagName().replaceAll(" ",""), Integer.toString(tag.getPlaylists().size()));
            triePLRef.add(tag.getTagName().replaceAll(" ",""), tag.getPlaylists());
        }

    }

    @RequestMapping(path = "/trieOcc/{name}", method = RequestMethod.GET)
    public String getTrieOcc(@PathVariable("name") String str) throws IOException{
        List<Node> nodeList = trieOcc.prefixedWords(str);
        tagList.clear();
        matchedTags.clear();
        tagListPredicted.clear();

        for(Node n : nodeList){
            tagList.add(new NodeTuple(n.nodeName +" --------------  " + n.value+ ((n.value.equals("1"))?" playlist":" playlists")));
        }

        for(Node n : nodeList){
            matchedTags.add(n.nodeName);
        }
        for(String tag: Tags){
            if(!matchedTags.contains(tag)){
                if(tag.startsWith(str)){
                    tagListPredicted.add(new NodeTuple(tag));
                }
            }
        }
        for (String tag: Tags){
            if(!matchedTags.contains(tag)){
                if(tag.contains(str)){
                    tagListPredicted.add(new NodeTuple(tag));
                }
            }
        }
        tagList.addAll(tagListPredicted);
        System.out.println("writeListToJsonArray(tagList)=====>"+writeListToJsonArray(tagList));
        return writeListToJsonArray(tagList);
    }

    public String writeListToJsonArray( List<NodeTuple> list) throws IOException {


        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, list);

        final byte[] data = out.toByteArray();
        return (new String(data));
    }

    @RequestMapping(path = "/playlists", method = RequestMethod.GET)
    public String getPlaylists(@RequestParam("myInput") String selectedTag, Model model, RedirectAttributes redirectAttributes) throws IOException{
        relevantPlaylists.clear();
        if(selectedTag.contains(" ----"))
            selectedTag = selectedTag.substring(0,selectedTag.indexOf(" ----"));

        Node selectedNode = triePLRef.getNode(selectedTag);
        Set<Playlist> pLists = selectedNode.playlists;

        for (Playlist p : pLists){
            relevantPlaylists.add(new NodeTuple(p.getPlaylistName() + " ---------- "+(p.getNoOfViews()==0?"No Views":(p.getNoOfViews()==1?"1 View":Integer.toString(p.getNoOfViews())+" Views"))));
        }
        System.out.println("writeListToJsonArray(tagList)=====>"+writeListToJsonArray(relevantPlaylists));
        return writeListToJsonArray(relevantPlaylists);
    }

}
