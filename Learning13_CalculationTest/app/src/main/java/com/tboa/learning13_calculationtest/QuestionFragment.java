package com.tboa.learning13_calculationtest;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.tboa.learning13_calculationtest.databinding.FragmentQuestionBinding;
import com.tboa.learning13_calculationtest.databinding.FragmentTitleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        if (!mainViewModel.getOnAnswering().getValue()) {
            mainViewModel.generator();
        }
        final FragmentQuestionBinding binding;
        final boolean[] isAnswered = {false};
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(mainViewModel);
        binding.setLifecycleOwner(requireActivity());
        final StringBuilder builder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAnswered[0] = true;
                switch (view.getId()) {
                    case R.id.button1:
                        builder.append(1);
                        break;
                    case R.id.button2:
                        builder.append(2);
                        break;
                    case R.id.button3:
                        builder.append(3);
                        break;
                    case R.id.button4:
                        builder.append(4);
                        break;
                    case R.id.button5:
                        builder.append(5);
                        break;
                    case R.id.button6:
                        builder.append(6);
                        break;
                    case R.id.button7:
                        builder.append(7);
                        break;
                    case R.id.button8:
                        builder.append(8);
                        break;
                    case R.id.button9:
                        builder.append(9);
                        break;
                    case R.id.button10:
                        builder.setLength(0);
                        isAnswered[0] = false;
                        break;
                    case R.id.button11:
                        builder.append(0);
                        break;
                    default:
                        break;
                }
                if (builder.length() == 0) {
                    binding.textView9.setText(getString(R.string.input_indicator));
                } else {
                    binding.textView9.setText(builder.toString());
                }
            }
        };
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.button10.setOnClickListener(listener);
        binding.button11.setOnClickListener(listener);

        binding.button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAnswered[0] == true) {
                    if (Integer.valueOf(builder.toString()).intValue() == mainViewModel.getAnswer().getValue()) {
                        mainViewModel.answerCorrect();
                        builder.setLength(0);
                        binding.textView9.setText(getString(R.string.answer_current_message));
                    } else {
                        NavController controller = Navigation.findNavController(view);
                        if (mainViewModel.winFlag) {
                            mainViewModel.winFlag = false;
                            mainViewModel.save();
                            controller.navigate(R.id.action_questionFragment_to_winFragment);
                        } else {
                            controller.navigate(R.id.action_questionFragment_to_loseFragment);
                        }
                    }
                } else {
                    Toast.makeText(requireActivity(), getString(R.string.answer_hint), Toast.LENGTH_LONG).show();
                }
            }
        });
        return binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_question, container, false);
    }
}