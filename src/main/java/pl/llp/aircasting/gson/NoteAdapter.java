/**
    AirCasting - Share your Air!
    Copyright (C) 2011-2012 HabitatMap, Inc.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    You can contact the authors by email at <info@habitatmap.org>
*/
package pl.llp.aircasting.gson;

import com.google.gson.*;
import pl.llp.aircasting.model.Note;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: obrok
 * Date: 1/3/12
 * Time: 1:19 PM
 */
public class NoteAdapter implements JsonDeserializer<Note>, JsonSerializer<Note> {
    @Override
    public Note deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        Note note = new Note();

        JsonObject object = element.getAsJsonObject();

        note.setDate((Date) context.deserialize(object.get("date"), Date.class));
        note.setNumber(object.get("number").getAsInt());
        note.setLatitude(object.get("latitude").getAsDouble());
        note.setLongitude(object.get("longitude").getAsDouble());
        note.setText((String) context.deserialize(object.get("text"), String.class));
        note.setPhotoPath((String) context.deserialize(object.get("photo_location"), String.class));

        return note;
    }

    @Override
    public JsonElement serialize(Note note, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("number", note.getNumber());
        object.addProperty("latitude", note.getLatitude());
        object.addProperty("longitude", note.getLongitude());
        object.add("date", context.serialize(note.getDate()));
        object.addProperty("text", note.getText());

        return object;
    }
}
