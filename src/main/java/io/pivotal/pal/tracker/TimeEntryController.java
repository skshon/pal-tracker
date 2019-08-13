package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable(name="id") Long timeEntryId)
    {
        TimeEntry entry = timeEntryRepository.find(timeEntryId);
        ResponseEntity entity = null;

        if( entry != null ) {
            entity = new ResponseEntity(entry, HttpStatus.OK);
        } else {
            entity = new ResponseEntity(entry, HttpStatus.NOT_FOUND);
        }
        return entity;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry entry = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable(name="id") Long timeEntryId, @RequestBody TimeEntry entry){
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, entry);
        if( timeEntry == null ){
            return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable(name="id") Long timeEntryId){
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
